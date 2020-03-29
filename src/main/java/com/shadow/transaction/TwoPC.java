package com.shadow.transaction;

import com.mysql.jdbc.jdbc2.optional.MysqlXAConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TwoPC {
    public static void main(String[] args) throws SQLException {
        // true 表示大于XA语句，用于测试
        boolean logXaCommands = true;
        // 获得资源管理器操作接口实列RM1
        Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_transaction1", "root", "root");
        XAConnection xaConn1 = new MysqlXAConnection((com.mysql.jdbc.Connection) conn1, logXaCommands);
        XAResource rm1 = xaConn1.getXAResource();
        // 获得资源管理器操作接口实列RM2
        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_transaction2", "root", "root");
        XAConnection xaConn2 = new MysqlXAConnection((com.mysql.jdbc.Connection) conn2, logXaCommands);
        XAResource rm2 = xaConn2.getXAResource();
        // AP请求TM执行一个分布式事务，TM生成全局事务id
        byte[] gtrid = "shadow".getBytes();
        int formatId = 1;
        try {
            // 分别执行RM1和RM2上的事务分支
            // TM生成rm1上的事务分支id
            byte[] bqual1 = "b00001".getBytes();
            Xid xid1 = new MysqlXid(gtrid, bqual1, formatId);
            // 执行rm1上的事务分支
            rm1.start(xid1, XAResource.TMNOFLAGS);// TMNOFLAGS / TMJOIN / TMRESUME
            PreparedStatement ps1 = conn1.prepareStatement("INSERT INTO `user`(name) VALUES (?)");
            ps1.execute();
            rm1.end(xid1, XAResource.TMSUCCESS);

            // TM生成rm2上的事务分支id
            byte[] bqual2 = "b00002".getBytes();
            Xid xid2 = new MysqlXid(gtrid, bqual2, formatId);
            // 执行rm1上的事务分支
            rm2.start(xid2, XAResource.TMNOFLAGS);// TMNOFLAGS / TMJOIN / TMRESUME
            PreparedStatement ps2 = conn1.prepareStatement("INSERT INTO account(name) VALUES (?)");
            ps2.execute();
            rm2.end(xid2, XAResource.TMSUCCESS);

            // 两阶段提交
            // 询问所有RM，准备提交事务分支
            int rm1_prepare = rm1.prepare(xid1);
            int rm2_prepare = rm2.prepare(xid2);
            // 提交所有事务分支
            boolean onePhase = false;
            // 所有事务分支prepare 成功，提交所有事务
            if (rm1_prepare == XAResource.XA_OK && rm2_prepare == XAResource.XA_OK) {
                rm1.commit(xid1, onePhase);
                rm2.commit(xid2, onePhase);
            } else {
                rm1.rollback(xid1);
                rm2.rollback(xid2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 出现异常也要回滚
        }
    }
}
