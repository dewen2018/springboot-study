package com.dewen.ldap;

import com.dewen.ldap.jdbc.Jdbc;
import com.dewen.ldap.jdbc.SqlUtil;
import com.dewen.ldap.jdbc.StatementParameter;
import com.dewen.ldap.jdbc.builder.InsertBuilder;
import com.dewen.ldap.jdbc.builder.QueryBuilder;
import com.dewen.ldap.jdbc.builder.UpdateBuilder;
import com.dewen.ldap.jdbc.builder.WhereBuilder;
import com.dewen.ldap.lang.Paging;
import com.dewen.ldap.lang.PagingImpl;
import com.dewen.ldap.nineox.util.*;
import com.dewen.ldap.util.ParameterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@SuppressWarnings("unchecked")
@Slf4j
public class DaoSupport {
    private String ORDERBY_DEFAULT_COLUMN = "createTime";

    @Autowired
    public Jdbc jdbc;


    /**************************************************************/

    /**
     * 获取记录数量
     *
     * @param tableName
     * @param paramNames
     * @param paramValues
     * @return
     */
    public long count(String tableName, String[] paramNames, Object[] paramValues) {
        WhereBuilder builder = WhereBuilderUtil.create(paramNames, paramValues);
        if (builder == null) {
            String sql = "SELECT count(*) FROM " + tableName;
            return jdbc.queryForLong(sql);
        } else {
            String sql = "SELECT count(*) FROM " + tableName + " where " + builder.getSql();
            return jdbc.queryForLong(sql, builder.getParam());
        }
    }

    /**
     * 获取记录数量
     *
     * @param tableName
     * @param queryBuilder
     * @return
     */
    public long count(String tableName, QueryBuilder queryBuilder) {
        WhereBuilder builder = WhereBuilderUtil.create(queryBuilder);
        if (builder == null) {
            String sql = "SELECT count(*) FROM " + tableName;
            return jdbc.queryForLong(sql);
        } else {
            String sql = "SELECT count(*) FROM " + tableName + " where " + builder.getSql();
            return jdbc.queryForLong(sql, builder.getParam());
        }
    }

    /**
     * 获取记录数量
     *
     * @param sql
     * @param paramValues
     * @return
     */
    public long countBySql(String sql, Object[] paramValues) {
        return jdbc.queryForLong(sql, StatementParameterUtil.create(paramValues));
    }

    /**
     * 获取记录数量
     *
     * @param sql
     * @param paramValues
     * @return
     */
    public long countBySql(String sql, String[] paramNames, Object[] paramValues) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return countBySql(sql, paramValues);
        } else {
            long count = 0;
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            count = jdbc.queryForLong(newSql, handleParameter(newParamNames, paramNames, paramValues));
            return count;
        }
    }

    /**
     * 通过ID从数据库中检索一个对象
     *
     * @param tableName 查询语句
     * @param clazz     对象类型
     * @param id        对象的标示符
     * @return Object 返回一个对象
     */
    public <T> T get(Class<T> clazz, String tableName, Object id) {
        if (id == null) {
            return null;
        } else {
            String sql = "select * from " + tableName + " where id=? ";
            return jdbc.query(sql, clazz, new Object[]{id});
        }
    }

    /**
     * 从数据库中检索一个对象
     *
     * @param clazz
     * @param tableName
     * @param paramNames
     * @param paramValues
     * @return
     */
    public <T> T get(Class<T> clazz, String tableName, String[] paramNames, Object[] paramValues) {
        WhereBuilder builder = WhereBuilderUtil.create(paramNames, paramValues);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName;
            return jdbc.query(sql, clazz);
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql();
            return jdbc.query(sql, clazz, builder.getParam());
        }
    }

    /**
     * 从数据库中检索一个对象
     *
     * @param clazz
     * @param tableName
     * @param queryBuilder
     * @return
     */
    public <T> T get(Class<T> clazz, String tableName, QueryBuilder queryBuilder) {
        WhereBuilder builder = WhereBuilderUtil.create(queryBuilder);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName;
            return jdbc.query(sql, clazz);
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql();
            return jdbc.query(sql, clazz, builder.getParam());
        }
    }

    /**
     * 从数据库中检索一个对象
     *
     * @param clazz
     * @param sql
     * @return
     */
    public <T> T getBySql(Class<T> clazz, String sql) {
        if (clazz.isAssignableFrom(Long.class)) {
            return (T) jdbc.queryForLong(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(Integer.class)) {
            return (T) jdbc.queryForInt(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(Double.class)) {
            return (T) jdbc.queryForDouble(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(Float.class)) {
            return (T) jdbc.queryForFloat(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(Date.class)) {
            return (T) jdbc.queryForDate(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(String.class)) {
            return (T) jdbc.queryForString(sql);
        } else {
            return jdbc.query(sql, clazz);
        }
    }

    /**
     * 从数据库中检索一个对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param clazz
     * @param sql
     * @param paramValues
     * @return
     */
    public <T> T getBySql(Class<T> clazz, String sql, Object[] paramValues) {
        StatementParameter parameter = StatementParameterUtil.create(paramValues);
        if (clazz.isAssignableFrom(Long.class)) {
            return (T) jdbc.queryForLong(sql, parameter);
        } else if (clazz.isAssignableFrom(Integer.class)) {
            return (T) jdbc.queryForInt(sql, parameter);
        } else if (clazz.isAssignableFrom(Double.class)) {
            return (T) jdbc.queryForDouble(sql, parameter);
        } else if (clazz.isAssignableFrom(Float.class)) {
            return (T) jdbc.queryForFloat(sql, parameter);
        } else if (clazz.isAssignableFrom(Date.class)) {
            return (T) jdbc.queryForDate(sql, parameter);
        } else if (clazz.isAssignableFrom(String.class)) {
            return (T) jdbc.queryForString(sql, parameter);
        } else {
            return jdbc.query(sql, clazz, parameter);
        }
    }

    /**
     * 从数据库中检索一个对象 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param clazz
     * @param sql
     * @param paramNames
     * @param paramValues
     * @return
     */
    public <T> T getBySql(Class<T> clazz, String sql, String[] paramNames, Object[] paramValues) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return null;
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            StatementParameter parameter = handleParameter(newParamNames, paramNames, paramValues);
            if (clazz.isAssignableFrom(Long.class)) {
                return (T) jdbc.queryForLong(newSql, parameter);
            } else if (clazz.isAssignableFrom(Integer.class)) {
                return (T) jdbc.queryForInt(newSql, parameter);
            } else if (clazz.isAssignableFrom(Double.class)) {
                return (T) jdbc.queryForDouble(newSql, parameter);
            } else if (clazz.isAssignableFrom(Float.class)) {
                return (T) jdbc.queryForFloat(newSql, parameter);
            } else if (clazz.isAssignableFrom(Date.class)) {
                return (T) jdbc.queryForDate(newSql, parameter);
            } else if (clazz.isAssignableFrom(String.class)) {
                return (T) jdbc.queryForString(newSql, parameter);
            } else {
                return jdbc.query(newSql, clazz, parameter);
            }
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @return
     */
    public <T> List<T> list(Class<T> clazz, String tableName) {
        String[] sortColumns = new String[]{ORDERBY_DEFAULT_COLUMN};
        boolean[] ascs = new boolean[]{false};
        String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
        return jdbc.queryForList(sql, clazz);
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> List<T> list(Class<T> clazz, String tableName, String[] sortColumns, boolean[] ascs) {
        String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
        return jdbc.queryForList(sql, clazz);
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param paramNames
     * @param paramValues
     * @return
     */
    public <T> List<T> list(Class<T> clazz, String tableName, String[] paramNames, Object[] paramValues) {
        String[] sortColumns = new String[]{ORDERBY_DEFAULT_COLUMN};
        boolean[] ascs = new boolean[]{false};
        WhereBuilder builder = WhereBuilderUtil.create(paramNames, paramValues);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForList(sql, clazz);
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql() + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForList(sql, clazz, builder.getParam());
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param paramNames
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> List<T> list(Class<T> clazz, String tableName, String[] paramNames, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        WhereBuilder builder = WhereBuilderUtil.create(paramNames, paramValues);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForList(sql, clazz);
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql() + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForList(sql, clazz, builder.getParam());
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param queryBuilder
     * @return
     */
    public <T> List<T> list(Class<T> clazz, String tableName, QueryBuilder queryBuilder) {
        String[] sortColumns = new String[]{ORDERBY_DEFAULT_COLUMN};
        boolean[] ascs = new boolean[]{false};
        WhereBuilder builder = WhereBuilderUtil.create(queryBuilder);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForList(sql, clazz);
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql() + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForList(sql, clazz, builder.getParam());
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param queryBuilder
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> List<T> list(Class<T> clazz, String tableName, QueryBuilder queryBuilder, String[] sortColumns, boolean[] ascs) {
        WhereBuilder builder = WhereBuilderUtil.create(queryBuilder);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForList(sql, clazz);
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql() + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForList(sql, clazz, builder.getParam());
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param sql
     * @return
     */
    public <T> List<T> listBySql(Class<T> clazz, String sql) {
        if (clazz.isAssignableFrom(Long.class)) {
            return (List<T>) jdbc.queryForLongs(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(Integer.class)) {
            return (List<T>) jdbc.queryForInts(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(String.class)) {
            return (List<T>) jdbc.queryForStrings(sql);
        } else {
            return jdbc.queryForList(sql, clazz);
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param sql
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> List<T> listBySql(Class<T> clazz, String sql, String[] sortColumns, boolean[] ascs) {
        sql = sql + OrderByUtil.getSql(sortColumns, ascs);
        if (clazz.isAssignableFrom(Long.class)) {
            return (List<T>) jdbc.queryForLongs(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(Integer.class)) {
            return (List<T>) jdbc.queryForInts(sql, new StatementParameter());
        } else if (clazz.isAssignableFrom(String.class)) {
            return (List<T>) jdbc.queryForStrings(sql);
        } else {
            return jdbc.queryForList(sql, clazz);
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param clazz
     * @param sql
     * @param paramValues
     * @return
     */
    public <T> List<T> listBySql(Class<T> clazz, String sql, Object[] paramValues) {
        StatementParameter parameter = StatementParameterUtil.create(paramValues);
        if (clazz.isAssignableFrom(Long.class)) {
            return (List<T>) jdbc.queryForLongs(sql, parameter);
        } else if (clazz.isAssignableFrom(Integer.class)) {
            return (List<T>) jdbc.queryForInts(sql, parameter);
        } else if (clazz.isAssignableFrom(String.class)) {
            return (List<T>) jdbc.queryForStrings(sql, parameter);
        } else {
            return jdbc.queryForList(sql, clazz, parameter);
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param clazz
     * @param sql
     * @param paramNames
     * @param paramValues
     * @return
     */
    public <T> List<T> listBySql(Class<T> clazz, String sql, String[] paramNames, Object[] paramValues) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return null;
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            StatementParameter parameter = handleParameter(newParamNames, paramNames, paramValues);
            if (clazz.isAssignableFrom(Long.class)) {
                return (List<T>) jdbc.queryForLongs(newSql, parameter);
            } else if (clazz.isAssignableFrom(Integer.class)) {
                return (List<T>) jdbc.queryForInts(newSql, parameter);
            } else if (clazz.isAssignableFrom(String.class)) {
                return (List<T>) jdbc.queryForStrings(newSql, parameter);
            } else {
                return jdbc.queryForList(newSql, clazz, parameter);
            }
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param clazz
     * @param sql
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> List<T> listBySql(Class<T> clazz, String sql, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        sql = sql + OrderByUtil.getSql(sortColumns, ascs);
        StatementParameter parameter = StatementParameterUtil.create(paramValues);
        if (clazz.isAssignableFrom(Long.class)) {
            return (List<T>) jdbc.queryForLongs(sql, parameter);
        } else if (clazz.isAssignableFrom(Integer.class)) {
            return (List<T>) jdbc.queryForInts(sql, parameter);
        } else if (clazz.isAssignableFrom(String.class)) {
            return (List<T>) jdbc.queryForStrings(sql, parameter);
        } else {
            return jdbc.queryForList(sql, clazz, parameter);
        }
    }

    /**
     * 获取数量 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param sql
     * @param paramNames
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> List<T> listBySql(Class<T> clazz, String sql, String[] paramNames, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return null;
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            newSql = newSql + OrderByUtil.getSql(sortColumns, ascs);
            StatementParameter parameter = handleParameter(newParamNames, paramNames, paramValues);
            if (clazz.isAssignableFrom(Long.class)) {
                return (List<T>) jdbc.queryForLongs(newSql, parameter);
            } else if (clazz.isAssignableFrom(Integer.class)) {
                return (List<T>) jdbc.queryForInts(newSql, parameter);
            } else if (clazz.isAssignableFrom(String.class)) {
                return (List<T>) jdbc.queryForStrings(newSql, parameter);
            } else {
                return jdbc.queryForList(newSql, clazz, parameter);
            }
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> listBySql(String sql) {
        return jdbc.queryForMaps(sql);
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @param paramValues
     * @return
     */
    public List<Map<String, Object>> listBySql(String sql, Object[] paramValues) {
        return jdbc.queryForMaps(sql, paramValues);
    }

    /**
     * 获取数量 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param sql
     * @param paramNames
     * @param paramValues
     * @return
     */
    public List<Map<String, Object>> listBySql(String sql, String[] paramNames, Object[] paramValues) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return null;
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            StatementParameter statementParameter = handleParameter(newParamNames, paramNames, paramValues);
            return jdbc.queryForMaps(newSql, statementParameter.getArgs());
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public List<Map<String, Object>> listBySql(String sql, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        sql = sql + OrderByUtil.getSql(sortColumns, ascs);
        return jdbc.queryForMaps(sql, paramValues);
    }

    /**
     * 获取数量 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param sql
     * @param paramNames
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public List<Map<String, Object>> listBySql(String sql, String[] paramNames, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return null;
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            newSql = newSql + OrderByUtil.getSql(sortColumns, ascs);
            StatementParameter statementParameter = handleParameter(newParamNames, paramNames, paramValues);
            return jdbc.queryForMaps(newSql, statementParameter.getArgs());
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param start
     * @param size
     * @return
     */
    public <T> Paging<T> paging(Class<T> clazz, String tableName, int start, int size) {
        String[] sortColumns = new String[]{ORDERBY_DEFAULT_COLUMN};
        boolean[] ascs = new boolean[]{false};
        String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
        Paging<T> paging = jdbc.queryForPaging(sql, clazz, StatementParameterUtil.create(null), start, size);
        return paging;
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param start
     * @param size
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> Paging<T> paging(Class<T> clazz, String tableName, int start, int size, String[] sortColumns, boolean[] ascs) {
        String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
        Paging<T> page = jdbc.queryForPaging(sql, clazz, StatementParameterUtil.create(null), start, size);
        return page;
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param start
     * @param size
     * @param paramNames
     * @param paramValues
     * @return
     */
    public <T> Paging<T> paging(Class<T> clazz, String tableName, int start, int size, String[] paramNames, Object[] paramValues) {
        String[] sortColumns = new String[]{ORDERBY_DEFAULT_COLUMN};
        boolean[] ascs = new boolean[]{false};
        WhereBuilder builder = WhereBuilderUtil.create(paramNames, paramValues);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
            Paging<T> page = jdbc.queryForPaging(sql, clazz, new StatementParameter(), start, size);
            return page;
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql() + OrderByUtil.getSql(sortColumns, ascs);
            Paging<T> page = jdbc.queryForPaging(sql, clazz, builder.getParam(), start, size);
            return page;
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param start
     * @param size
     * @param paramNames
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> Paging<T> paging(Class<T> clazz, String tableName, int start, int size, String[] paramNames, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        WhereBuilder builder = WhereBuilderUtil.create(paramNames, paramValues);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
            Paging<T> page = jdbc.queryForPaging(sql, clazz, new StatementParameter(), start, size);
            return page;
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql() + OrderByUtil.getSql(sortColumns, ascs);
            Paging<T> page = jdbc.queryForPaging(sql, clazz, builder.getParam(), start, size);
            return page;
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param start
     * @param size
     * @param queryBuilder
     * @return
     */
    public <T> Paging<T> paging(Class<T> clazz, String tableName, int start, int size, QueryBuilder queryBuilder) {
        String[] sortColumns = new String[]{ORDERBY_DEFAULT_COLUMN};
        boolean[] ascs = new boolean[]{false};
        WhereBuilder builder = WhereBuilderUtil.create(queryBuilder);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
            log.info(sql);
            Paging<T> page = jdbc.queryForPaging(sql, clazz, new StatementParameter(), start, size);
            return page;
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql() + OrderByUtil.getSql(sortColumns, ascs);
            log.info(sql);
            Paging<T> page = jdbc.queryForPaging(sql, clazz, builder.getParam(), start, size);
            return page;
        }
    }

    /**
     * 从数据库中检索一组对象
     *
     * @param clazz
     * @param tableName
     * @param start
     * @param size
     * @param queryBuilder
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> Paging<T> paging(Class<T> clazz, String tableName, int start, int size, QueryBuilder queryBuilder, String[] sortColumns, boolean[] ascs) {
        WhereBuilder builder = WhereBuilderUtil.create(queryBuilder);
        if (builder == null) {
            String sql = "SELECT * FROM " + tableName + OrderByUtil.getSql(sortColumns, ascs);
            Paging<T> page = jdbc.queryForPaging(sql, clazz, new StatementParameter(), start, size);
            return page;
        } else {
            String sql = "SELECT * FROM " + tableName + " where " + builder.getSql() + OrderByUtil.getSql(sortColumns, ascs);
            Paging<T> page = jdbc.queryForPaging(sql, clazz, builder.getParam(), start, size);
            return page;
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param clazz
     * @param sql
     * @param start
     * @param size
     * @param param
     * @return
     */
    public <T> Paging<T> pagingBySql(Class<T> clazz, String sql, int start, int size, StatementParameter param) {
        Paging<T> page = jdbc.queryForPaging(sql, clazz, param, start, size);
        return page;
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param clazz
     * @param sql
     * @param start
     * @param size
     * @param param
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> Paging<T> pagingBySql(Class<T> clazz, String sql, int start, int size, StatementParameter param, String[] sortColumns, boolean[] ascs) {
        sql = sql + OrderByUtil.getSql(sortColumns, ascs);
        Paging<T> page = jdbc.queryForPaging(sql, clazz, param, start, size);
        return page;
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param clazz
     * @param sql
     * @param start
     * @param size
     * @param paramValues
     * @return
     */
    public <T> Paging<T> pagingBySql(Class<T> clazz, String sql, int start, int size, Object[] paramValues) {
        log.info(sql);
        Paging<T> page = jdbc.queryForPaging(sql, clazz, StatementParameterUtil.create(paramValues), start, size);
        return page;
    }

    /**
     * 获取数量 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param clazz
     * @param sql
     * @param start
     * @param size
     * @param paramNames
     * @param paramValues
     * @return
     */
    public <T> Paging<T> pagingBySql(Class<T> clazz, String sql, int start, int size, String[] paramNames, Object[] paramValues) {
        log.info(sql);
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return jdbc.queryForPaging(sql, clazz, paramValues, start, size);
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            return jdbc.queryForPaging(newSql, clazz, handleParameter(newParamNames, paramNames, paramValues), start, size);
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param clazz
     * @param sql
     * @param start
     * @param size
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> Paging<T> pagingBySql(Class<T> clazz, String sql, int start, int size, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        sql = sql + OrderByUtil.getSql(sortColumns, ascs);
        log.info(sql);
        Paging<T> page = jdbc.queryForPaging(sql, clazz, StatementParameterUtil.create(paramValues), start, size);
        return page;
    }

    /**
     * 获取数量 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param clazz
     * @param sql
     * @param start
     * @param size
     * @param paramNames
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public <T> Paging<T> pagingBySql(Class<T> clazz, String sql, int start, int size, String[] paramNames, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        log.info(sql);
        if (map == null) {
            return jdbc.queryForPaging(sql, clazz, paramValues, start, size, OrderByUtil.getSql(sortColumns, ascs));
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            newSql = newSql + OrderByUtil.getSql(sortColumns, ascs);
            return jdbc.queryForPaging(newSql, clazz, handleParameter(newParamNames, paramNames, paramValues), start, size);
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @param start
     * @param size
     * @return
     */
    public Paging<Map<String, Object>> pagingBySql(String sql, int start, int size) {
        String countSql = SqlUtil.toCountSql(sql);
        // 获取总行数
        int rowCount = jdbc.queryForInt(countSql);
        if (rowCount == 0) {
            return new PagingImpl<Map<String, Object>>(0);
        }
        String newSql = this.appendLimitSql(sql, start, size);
        PagingImpl<Map<String, Object>> paging = new PagingImpl<Map<String, Object>>();
        List<Map<String, Object>> list = listBySql(newSql);
        paging.setList(list);
        return paging;
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @param paramValues
     * @param start
     * @param size
     * @return
     */
    public Paging<Map<String, Object>> pagingBySql(String sql, Object[] paramValues, int start, int size) {
        String countSql = SqlUtil.toCountSql(sql);
        // 获取总行数
        int rowCount = jdbc.queryForInt(countSql, paramValues);
        if (rowCount == 0) {
            return new PagingImpl<Map<String, Object>>(0);
        }
        String newSql = this.appendLimitSql(sql, start, size);
        PagingImpl<Map<String, Object>> paging = new PagingImpl<Map<String, Object>>();
        List<Map<String, Object>> list = listBySql(newSql, paramValues);
        paging.setList(list);
        return paging;
    }

    /**
     * 获取数量 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param sql
     * @param paramNames
     * @param paramValues
     * @param start
     * @param size
     * @return
     */
    public Paging<Map<String, Object>> pagingBySql(String sql, String[] paramNames, Object[] paramValues, int start, int size) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return pagingBySql(sql, paramValues, start, size);
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            newSql = this.appendLimitSql(newSql, start, size);
            PagingImpl<Map<String, Object>> paging = new PagingImpl<Map<String, Object>>();
            List<Map<String, Object>> list = jdbc.queryForMaps(newSql, handleParameter(newParamNames, paramNames, paramValues));
            paging.setList(list);
            return paging;
        }
    }

    /**
     * 从数据库中检索一组对象 该方法适合使用问号“?”来表示参数的sql语句 如：select * from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @param paramValues
     * @param start
     * @param size
     * @param sortColumns
     * @param ascs
     * @return
     */
    public Paging<Map<String, Object>> pagingBySql(String sql, Object[] paramValues, int start, int size, String[] sortColumns, boolean[] ascs) {
        sql = sql + OrderByUtil.getSql(sortColumns, ascs);
        sql = this.appendLimitSql(sql, start, size);
        PagingImpl<Map<String, Object>> paging = new PagingImpl<Map<String, Object>>();
        List<Map<String, Object>> list = jdbc.queryForMaps(sql, paramValues);
        paging.setList(list);
        return paging;
    }

    /**
     * 获取数量 该方法适合使用冒号“:”来表示参数的sql语句 如：select * table from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param sql
     * @param start
     * @param size
     * @param paramNames
     * @param paramValues
     * @param sortColumns
     * @param ascs
     * @return
     */
    public Paging<Map<String, Object>> pagingBySql(String sql, int start, int size, String[] paramNames, Object[] paramValues, String[] sortColumns, boolean[] ascs) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return pagingBySql(sql, paramValues, start, size, sortColumns, ascs);
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            newSql = newSql + OrderByUtil.getSql(sortColumns, ascs);
            newSql = this.appendLimitSql(newSql, start, size);
            PagingImpl<Map<String, Object>> paging = new PagingImpl<Map<String, Object>>();
            List<Map<String, Object>> list = jdbc.queryForMaps(newSql, handleParameter(newParamNames, paramNames, paramValues));
            paging.setList(list);
            return paging;
        }
    }

    /**
     * 添加对象到数据库
     *
     * @param object    对象
     * @param tableName
     */
    public <T> boolean add(T object, String tableName) {
        InsertBuilder builder = InsertBuilderUtil.create(object, tableName);
        boolean flag = jdbc.insertForBoolean(builder);
        return flag;
    }

    public <T> long insert(T object, String tableName) {
        InsertBuilder builder = InsertBuilderUtil.create(object, tableName);
        long n = jdbc.insertForLastId(builder.getSql(), builder.getParam());
        return n;
    }

    /**
     * 批量插入
     *
     * @param sql
     * @param list
     * @return
     */
    public <T> int[] add(String sql, List<T> list) {
        return jdbc.batchUpdate(sql, new MyBatchPreparedStatementSetter<>(list));
    }

    /**
     * 修改数据库对象
     *
     * @param object
     * @param tableName
     * @return
     */
    public <T> boolean update(T object, String tableName) {
        UpdateBuilder builder = UpdateBuilderUtil.create(object, tableName);
        boolean flag = jdbc.updateForBoolean(builder);
        return flag;
    }

    /**
     * 修改数据库对象 该方法适合使用问号“?”来表示参数的sql语句 如：update table set id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @param paramValues
     * @return
     */
    public boolean updateBySql(String sql, Object[] paramValues) {
        boolean flag = jdbc.updateForBoolean(sql, StatementParameterUtil.create(paramValues));
        return flag;
    }

    /**
     * 修改数据库对象 该方法适合使用问号“?”来表示参数的sql语句 如：update table (中间有其他的sql语句) set id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @param paramValues
     * @return
     */
    public boolean updateBySql(String sql, Object[] paramValues, String bizCode) {
        boolean flag = jdbc.updateForBoolean(sql, StatementParameterUtil.create(paramValues));
        return flag;
    }

    /**
     * 修改数据库对象 该方法适合使用冒号“:”来表示参数的sql语句 如：update where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param sql
     * @param paramNames
     * @param paramValues
     * @return
     */
    public boolean updateBySql(String sql, String[] paramNames, Object[] paramValues) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return false;
        } else {
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            boolean flag = jdbc.updateForBoolean(newSql, handleParameter(newParamNames, paramNames, paramValues));
            return flag;
        }
    }

    /**
     * 从数据库中删除对象
     *
     * @param tableName
     * @return
     */
    public boolean delete(String tableName) {
        boolean flag = false;
        String sql = "delete from " + tableName;
        flag = jdbc.updateForBoolean(sql);
        return flag;
    }

    /**
     * 通过id从数据库中删除对象
     *
     * @param tableName
     * @param id
     * @return
     */
    public boolean delete(String tableName, Object id) {
        String sql = "delete from " + tableName + " where id=?";
//		StatementParameter param = new StatementParameter();
//		param.setObject(id);
        boolean flag = jdbc.updateForBoolean(sql, id);
        return flag;
    }

    /**
     * 通过id从数据库中删除对象
     *
     * @param tableName
     * @param ids
     * @return
     */
    public boolean delete(String tableName, List<Object> ids) {
        boolean flag = false;
        for (Object id : ids) {
            if (flag) {
                delete(tableName, id);
            } else {
                flag = delete(tableName, id);
            }
        }
        return flag;
    }

    /**
     * 从数据库中删除对象
     *
     * @param tableName
     * @param paramNames
     * @param paramValues
     * @return
     */
    public boolean delete(String tableName, String[] paramNames, Object[] paramValues) {
        WhereBuilder builder = WhereBuilderUtil.create(paramNames, paramValues);
        if (builder == null) {
            return false;
        }
        boolean flag = false;
        // 把可删除的数据彻底删除
        String sql = "delete from " + tableName + " where " + builder.getSql();
        flag = jdbc.updateForBoolean(sql, builder.getParam());
        return flag;
    }

    /**
     * 从数据库中删除数据
     *
     * @param sql
     * @return
     */
    public boolean deleteBySql(String sql) {
        boolean flag = false;
        flag = jdbc.updateForBoolean(sql);
        return flag;
    }

    /**
     * 从数据库中删除数据 该方法适合使用问号“?”来表示参数的sql语句 如：delete from table where id=? 注：参数值的顺序必须和问号的顺序相同，否则会出错 如果参数比较少，强烈建议使用该方法，因为不用解析sql，性能会比较好。
     *
     * @param sql
     * @param paramValues
     * @return
     */
    public boolean deleteBySql(String sql, Object[] paramValues) {
        boolean flag = false;
        flag = jdbc.updateForBoolean(sql, StatementParameterUtil.create(paramValues));
        return flag;
    }

    /**
     * 从数据库中删除数据 该方法适合使用冒号“:”来表示参数的sql语句 如：delete from where id = :id 如果参数比较多，且出现重复的，可以使用该方法
     *
     * @param sql
     * @param paramNames
     * @param paramValues
     * @return
     */
    public boolean deleteBySql(String sql, String[] paramNames, Object[] paramValues) {
        Map<String, Object> map = handleSql(sql, paramNames, paramValues);
        if (map == null) {
            return deleteBySql(sql, paramValues);
        } else {
            boolean flag = false;
            String newSql = (String) map.get("newStr");
            List<String> newParamNames = (List<String>) map.get("paramNames");
            flag = jdbc.updateForBoolean(newSql, handleParameter(newParamNames, paramNames, paramValues));
            return flag;
        }
    }

    /**
     * 处理sql里的参数名
     *
     * @param sql
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Map<String, Object> handleSql(String sql, String[] paramNames, Object[] paramValues) {
        for (int i = 0; i < paramValues.length; i++) {
            String paramName = paramNames[i];
            Object paramValue = paramValues[i];
            // 判断paramValue的类型是不是集合，如果是集合，就要生成in查询
            if (paramValue instanceof List) {
                if (paramValue != null) {
                    String newParamName = null;
                    List list = (List) paramValue;
                    for (int j = list.size() - 1; j >= 0; j--) {
                        if (newParamName == null) {
                            newParamName = ":" + paramName + j;
                        } else {
                            newParamName = newParamName + "," + ":" + paramName + j;
                        }
                    }
                    sql = sql.replace(":" + paramName, newParamName);
                }
            } else if (paramValue instanceof Object[]) {
                if (paramValue != null) {
                    String newParamName = null;
                    Object[] array = (Object[]) paramValue;
                    for (int j = array.length; j >= 0; j--) {
                        if (newParamName == null) {
                            newParamName = paramName + j;
                        } else {
                            newParamName = newParamName + "," + paramName + j;
                        }
                    }
                    sql = sql.replace(paramName, newParamName);
                }
            }
        }
        return ParameterUtil.handleParameterName(ParameterUtil.PARAMETER_TYPE_COLON, sql, "?");
    }

    @SuppressWarnings("rawtypes")
    public StatementParameter handleParameter(List<String> newParamNames, String[] oldParamNames, Object[] paramValues) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < oldParamNames.length; i++) {
            String paramName = oldParamNames[i];
            Object paramValue = paramValues[i];
            if (paramValue instanceof List) {
                if (paramValue != null) {
                    List list = (List) paramValue;
                    for (int j = 0; j < list.size(); j++) {
                        map.put(paramName + j, list.get(j));
                    }
                }
            } else if (paramValue instanceof Object[]) {
                if (paramValue != null) {
                    Object[] array = (Object[]) paramValue;
                    for (int j = 0; j < array.length; j++) {
                        map.put(paramName + j, array[j]);
                    }
                }
            } else {
                map.put(paramName, paramValue);
            }
        }
        StatementParameter statementParameter = new StatementParameter();
        for (int i = 0; i < newParamNames.size(); i++) {
            StatementParameterUtil.setProperty(statementParameter, map.get(newParamNames.get(i)));
        }
        return statementParameter;
    }

    public String appendLimitSql(String sql, int start, int size) {
        if (sql.endsWith(";")) {
            sql = sql.substring(0, sql.length() - 1);
        }
        return sql + " LIMIT " + start + "," + size + ";";
    }
}
