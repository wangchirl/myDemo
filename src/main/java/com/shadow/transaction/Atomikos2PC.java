package com.shadow.transaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.transaction.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class Atomikos2PC {

    public static void main(String[] args) throws SystemException {
        AtomikosDataSourceBean ds1 = createAtomikosDataSourceBean("db_transaction1");
        AtomikosDataSourceBean ds2 = createAtomikosDataSourceBean("db_transaction2");

        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        UserTransactionImp userTransactionImp = new UserTransactionImp();
        try {
            // 开启事务
            userTransactionImp.begin();

            // 执行db1的sql
            conn1 = ps1.getConnection();
            ps1 = conn1.prepareStatement("INSERT INTO user(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, "shadow");
            ps1.executeUpdate();

            // 模拟异常情况
//            int i = 10 / 0;

            // 执行db2的sql
            conn2 = ps2.getConnection();
            ps2 = conn2.prepareStatement("INSERT INTO account(name) VALUES (?)");
            ps2.setString(1, "shadow1111");
            ps2.executeUpdate();

            // 两阶段添加
            userTransactionImp.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 事务回滚
            userTransactionImp.rollback();
        } finally {
            try{
                ps1.close();
                ps2.close();
                conn1.close();
                conn2.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static AtomikosDataSourceBean createAtomikosDataSourceBean(String dbName) {
        // 连接基本属性
        Properties p = new Properties();
        p.setProperty("url", "jdbc:mysql://localhost:3306/" + dbName);
        p.setProperty("user", "root");
        p.setProperty("password", "root");
        // 使用AtomikosDataSourceBean封装com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        // atomikos 要求为每一个AtomikosDataSourceBean名称，为了方便记忆，这里设置为dbName相同
        ds.setUniqueResourceName(dbName);
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setXaProperties(p);
        return ds;
    }
}
