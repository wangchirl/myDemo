package com.shadow.aop;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * SPEL 表达式解析器
 */
public class SpelParser {


    private static ExpressionParser parser = new SpelExpressionParser();


    /**
     *
     * @param key el表达式字符串，占位符#开头
     * @param paramterNames 形参名称，可以理解为占位符名称
     * @param args 参数值，可以理解为占位符真实值
     * @return 返回el表达式经过参数替换后的字符串值
     */
    public static String getKey(String key,String[] paramterNames, Object[] args){
        // 1.将key字符串解析为el表达式
        Expression exp = parser.parseExpression(key);
        // 2.将形参和形参值以配对的方式配置到赋值上下文
        EvaluationContext context = new StandardEvaluationContext();// 初始化赋值上下文
        if(args.length <= 0){
            return null;
        }
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramterNames[i],args[i]);
        }
        // 3.根据赋值上下文运算el表达式
        return exp.getValue(context, String.class);
    }


    public static void main(String[] args) {
        String key = "#key + ' ' +#cacheName";
        String name1 = "key";
        String name2 = "cacheName";
        // paramters
        String[] names = new String[]{name1,name2};
        // args
        Object[] args1 = new Object[2];
        args1[0] = "realKey";
        args1[1] = "realCacheName";
        System.out.println(getKey(key,names,args1));
    }
}
