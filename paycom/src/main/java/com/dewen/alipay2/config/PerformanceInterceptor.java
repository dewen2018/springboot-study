//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dewen.alipay2.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.*;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Statement;
import java.util.*;

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "query",
        args = {Statement.class, ResultHandler.class}
), @Signature(
        type = StatementHandler.class,
        method = "update",
        args = {Statement.class}
), @Signature(
        type = StatementHandler.class,
        method = "batch",
        args = {Statement.class}
)})
public class PerformanceInterceptor implements Interceptor {
    private static final Log logger = LogFactory.getLog(PerformanceInterceptor.class);
    private static final String DruidPooledPreparedStatement = "com.alibaba.druid.pool.DruidPooledPreparedStatement";
    private static final String T4CPreparedStatement = "oracle.jdbc.driver.T4CPreparedStatement";
    private static final String OraclePreparedStatementWrapper = "oracle.jdbc.driver.OraclePreparedStatementWrapper";
    private long maxTime = 0L;
    private boolean format = false;
    private boolean writeInLog = false;
    private Method oracleGetOriginalSqlMethod;
    private Method druidGetSQLMethod;

    public PerformanceInterceptor() {
    }

    public Object intercept(Invocation invocation) throws Throwable {
        Object firstArg = invocation.getArgs()[0];
        Statement statement;
        if (Proxy.isProxyClass(firstArg.getClass())) {
            statement = (Statement) SystemMetaObject.forObject(firstArg).getValue("h.statement");
        } else {
            statement = (Statement) firstArg;
        }

        MetaObject stmtMetaObj = SystemMetaObject.forObject(statement);

        try {
            statement = (Statement) stmtMetaObj.getValue("stmt.statement");
        } catch (Exception var20) {
        }

        if (stmtMetaObj.hasGetter("delegate")) {
            try {
                statement = (Statement) stmtMetaObj.getValue("delegate");
            } catch (Exception var19) {
            }
        }

        String originalSql = null;
        String stmtClassName = statement.getClass().getName();
        Class clazz;
        Object stmtSql;
        if ("com.alibaba.druid.pool.DruidPooledPreparedStatement".equals(stmtClassName)) {
            try {
                if (this.druidGetSQLMethod == null) {
                    clazz = Class.forName("com.alibaba.druid.pool.DruidPooledPreparedStatement");
                    this.druidGetSQLMethod = clazz.getMethod("getSql");
                }

                stmtSql = this.druidGetSQLMethod.invoke(statement);
                if (stmtSql instanceof String) {
                    originalSql = (String) stmtSql;
                }
            } catch (Exception var18) {
                var18.printStackTrace();
            }
        } else if ("oracle.jdbc.driver.T4CPreparedStatement".equals(stmtClassName) || "oracle.jdbc.driver.OraclePreparedStatementWrapper".equals(stmtClassName)) {
            try {
                if (this.oracleGetOriginalSqlMethod != null) {
                    stmtSql = this.oracleGetOriginalSqlMethod.invoke(statement);
                    if (stmtSql instanceof String) {
                        originalSql = (String) stmtSql;
                    }
                } else {
                    clazz = Class.forName(stmtClassName);
                    this.oracleGetOriginalSqlMethod = this.getMethodRegular(clazz, "getOriginalSql");
                    if (this.oracleGetOriginalSqlMethod != null) {
                        this.oracleGetOriginalSqlMethod.setAccessible(true);
                        if (null != this.oracleGetOriginalSqlMethod) {
                            stmtSql = this.oracleGetOriginalSqlMethod.invoke(statement);
                            if (stmtSql instanceof String) {
                                originalSql = (String) stmtSql;
                            }
                        }
                    }
                }
            } catch (Exception var17) {
            }
        }

        if (originalSql == null) {
            originalSql = statement.toString();
        }

        originalSql = originalSql.replaceAll("[\\s]+", " ");
        int index = this.indexOfSqlStart(originalSql);
        if (index > 0) {
            originalSql = originalSql.substring(index);
        }

        long start = SystemClock.now();
        Object result = invocation.proceed();
        long timing = SystemClock.now() - start;
        Object target = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(target);
        MappedStatement ms = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        StringBuilder formatSql = (new StringBuilder()).append(" Time：").append(timing).append(" ms - ID：").append(ms.getId()).append("\n").append("Execute SQL：").append(SqlUtils.sqlFormat(originalSql, this.format)).append("\n");
        if (this.isWriteInLog()) {
            if (this.getMaxTime() >= 1L && timing > this.getMaxTime()) {
                logger.error(formatSql.toString());
            } else {
                logger.debug(formatSql.toString());
            }
        } else {
            System.err.println(formatSql.toString());
            Assert.isFalse(this.getMaxTime() >= 1L && timing > this.getMaxTime(), " The SQL execution time is too large, please optimize ! ", new Object[0]);
        }

        return result;
    }

    public Object plugin(Object target) {
        return target instanceof StatementHandler ? Plugin.wrap(target, this) : target;
    }

    public void setProperties(Properties prop) {
        String maxTime = prop.getProperty("maxTime");
        String format = prop.getProperty("format");
        if (StrUtil.isNotEmpty(maxTime)) {
            this.maxTime = Long.parseLong(maxTime);
        }

        if (StrUtil.isNotEmpty(format)) {
            this.format = Boolean.valueOf(format);
        }

    }

    public Method getMethodRegular(Class<?> clazz, String methodName) {
        if (Object.class.equals(clazz)) {
            return null;
        } else {
            Method[] var3 = clazz.getDeclaredMethods();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Method method = var3[var5];
                if (method.getName().equals(methodName)) {
                    return method;
                }
            }

            return this.getMethodRegular(clazz.getSuperclass(), methodName);
        }
    }

    private int indexOfSqlStart(String sql) {
        String upperCaseSql = sql.toUpperCase();
        Set<Integer> set = new HashSet();
        set.add(upperCaseSql.indexOf("SELECT "));
        set.add(upperCaseSql.indexOf("UPDATE "));
        set.add(upperCaseSql.indexOf("INSERT "));
        set.add(upperCaseSql.indexOf("DELETE "));
        set.remove(-1);
        if (CollectionUtils.isEmpty(set)) {
            return -1;
        } else {
            List<Integer> list = new ArrayList(set);
            list.sort(Comparator.naturalOrder());
            return (Integer) list.get(0);
        }
    }

    public PerformanceInterceptor setMaxTime(final long maxTime) {
        this.maxTime = maxTime;
        return this;
    }

    public long getMaxTime() {
        return this.maxTime;
    }

    public PerformanceInterceptor setFormat(final boolean format) {
        this.format = format;
        return this;
    }

    public boolean isFormat() {
        return this.format;
    }

    public PerformanceInterceptor setWriteInLog(final boolean writeInLog) {
        this.writeInLog = writeInLog;
        return this;
    }

    public boolean isWriteInLog() {
        return this.writeInLog;
    }
}
