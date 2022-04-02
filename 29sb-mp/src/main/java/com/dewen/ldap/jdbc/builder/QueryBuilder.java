package com.dewen.ldap.jdbc.builder;

import com.dewen.ldap.jdbc.Jdbc;
import com.dewen.ldap.jdbc.StatementParameter;
import com.dewen.ldap.lang.Paging;
import com.dewen.ldap.lang.TimeRange;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.Map.Entry;

public class QueryBuilder implements Cloneable {
    public QueryBuilder() {
    }

    public static String COMPOPERATOR_DENGYU = "=";// 等于
    public static String COMPOPERATOR_NOT_DENGYU = "!=";// 不等于
    public static String COMPOPERATOR_LIKE = "like";// 包含
    public static String COMPOPERATOR_DAYU = ">";// 大于
    public static String COMPOPERATOR_DAYU_DENGYU = ">=";// 大于等于
    public static String COMPOPERATOR_XIAOYU = "<";// 小于
    public static String COMPOPERATOR_XIAOYU_DENGYU = "<=";// 小于等于

    // 检索语句中的字段名
    private String[] name;
    // 检索语句中的运算符(默认是等号)
    private String[] operator;
    // 检索语句中的值
    private Object[] value;
    // 自定义条件语句
    protected String customCondition = null;

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getOperator() {
        return operator;
    }

    public void setOperator(String[] operator) {
        this.operator = operator;
    }

    public Object[] getValue() {
        return value;
    }

    public void setValue(Object[] value) {
        this.value = value;
    }

    public String getCustomCondition() {
        return customCondition;
    }

    public void setCustomCondition(String customCondition) {
        this.customCondition = customCondition;
    }

    public void add(String name, String operator, Object value) {
        if (value == null) {
            return;
        }
        List<String> names = this.name == null ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList(this.name));
        names.add(name);
        this.name = names.toArray(new String[]{});

        List<String> operators = this.operator == null ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList(this.operator));
        operators.add(operator);
        this.operator = operators.toArray(new String[]{});

        List<Object> values = this.value == null ? new ArrayList<Object>() : new ArrayList<Object>(Arrays.asList(this.value));
        values.add(value);
        this.value = values.toArray(new Object[]{});
    }

    /**
     * 生成map
     *
     * @return
     */
    public Map<String, Object> generateMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (name != null) {
            for (int i = 0; i < name.length; i++) {
                map.put(":" + name[i], value[i]);
            }
        }
        return map;
    }

    public boolean invail() {
        return name == null || name.length == 0 || value == null || value.length == 0;
    }

    public QueryBuilder clone() {
        QueryBuilder o = null;
        try {
            o = (QueryBuilder) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }


    private String tableName;

    private String rangeFieldName;
    private TimeRange range;

    private String orderFieldName;
    // 按desc 还是asc
    private String orderDirection;
    private String groupbyFieldName;

    private Integer limitStart;
    private Integer limitSize;

    private Map<String, Object> whereMap = new LinkedHashMap<String, Object>();

    private List<String> whereExpressionList = new ArrayList<String>();

    private Map<String, String> likeMap = new LinkedHashMap<String, String>();

    public QueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    public QueryBuilder range(String fieldName, TimeRange range) {
        if (range == null) {
            return this;
        }
        this.rangeFieldName = fieldName;
        this.range = range;
        return this;
    }

    public QueryBuilder addWhere(String expression) {
        whereExpressionList.add(expression);
        return this;
    }

    public QueryBuilder addString(String fieldName, String value) {
        return this.addString(fieldName, value, false);
    }

    public QueryBuilder addString(String fieldName, String value, boolean like) {
        if (StringUtils.hasLength(value)) {
            if (like) {
                this.addLike(fieldName, value);
            } else {
                this.addWhere(fieldName, value);
            }
        }
        return this;
    }

    public QueryBuilder addInt(String fieldName, int value) {
        if (value > 0) {
            this.addWhere(fieldName, value);
        }

        return this;
    }

    public QueryBuilder addBool(String fieldName, Boolean value) {
        if (value != null) {
            this.addWhere(fieldName, value);
        }

        return this;
    }


    public QueryBuilder addLong(String fieldName, long value) {
        if (value > 0) {
            this.addWhere(fieldName, value);
        }
        return this;
    }

    public QueryBuilder addWhere(String fieldName, Object value) {
        whereMap.put(fieldName, value);
        return this;
    }

    public QueryBuilder addLike(String fieldName, String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("参数不能为空.");
        }
        value = value.replace("%", "");
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("参数不能包含特殊字符[" + value + "].");
        }
        likeMap.put(fieldName, value);
        return this;
    }

//    public QueryBuilder addEnum(String fieldName, Inum inum) {
//        if (inum != null) {
//            this.addInt(fieldName, (Integer)inum.getKey());
//        }
//
//        return this;
//    }
//
//    public QueryBuilder addEnum(String fieldName, Snum snum) {
//        if (snum != null) {
//            this.addString(fieldName, (String)snum.getKey());
//        }
//
//        return this;
//    }

    //    public QueryBuilder addSnumList(String fieldName, List<?> snumList) {
//        if (snumList != null && !snumList.isEmpty()) {
//            List<String> keyList = new ArrayList();
//            Iterator var4 = snumList.iterator();
//
//            while(var4.hasNext()) {
//                Object snum = var4.next();
//                keyList.add(((Snum)snum).getKey());
//            }
//
//            return this.addStringList(fieldName, keyList);
//        } else {
//            return this;
//        }
//    }
    public QueryBuilder addStringList(String fieldName, List<String> valueList) {
        return valueList != null && !valueList.isEmpty() ? this.addWhere(fieldName, valueList) : this;
    }


    public QueryBuilder order(String fieldName) {
        return this.order(fieldName, "desc");
    }

    public QueryBuilder order(String fieldName, String orderDirection) {
        this.orderFieldName = fieldName;
        this.orderDirection = orderDirection;
        return this;
    }

    public QueryBuilder groupby(String fieldName) {
        this.groupbyFieldName = fieldName;
        return this;
    }

    public QueryBuilder limit(int start, int size) {
        this.limitStart = start;
        this.limitSize = size;
        return this;
    }

    protected String getRangeSQL(StatementParameter param) {
        StringBuilder rangeSQL = new StringBuilder();
        if (this.range != null) {
            if (range.getStartTime() != null) {
                rangeSQL.append(this.rangeFieldName + ">=?");
                param.setDate(range.getStartTime());
            }

            if (range.getEndTime() != null) {
                if (rangeSQL.length() > 0) {
                    rangeSQL.append(" and ");
                }
                rangeSQL.append(this.rangeFieldName + "<=?");
                param.setDate(range.getEndTime());
            }
        }

        return rangeSQL.toString();
    }

    protected String getWhereExpressionSQL() {
        if (this.whereExpressionList.isEmpty()) {
            return "";
        }
        StringBuilder whereSQL = new StringBuilder();
        for (String expression : this.whereExpressionList) {
            if (whereSQL.length() > 0) {
                whereSQL.append(" and ");
            }
            whereSQL.append(expression);
        }
        return whereSQL.toString();
    }

    protected String getWhereSQL(StatementParameter param) {
        StringBuilder whereSQL = new StringBuilder();
        for (Entry<String, Object> entry : this.whereMap.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();
            if (whereSQL.length() > 0) {
                whereSQL.append(" and ");
            }
            if (value instanceof List) {
                @SuppressWarnings("rawtypes")
                List list = (List) value;
                String sql = this.getWhereInSql(fieldName, list);
                whereSQL.append(" ").append(sql);
            } else {
                whereSQL.append(fieldName).append("=?");
                param.setObject(value.getClass(), value);
            }
        }
        return whereSQL.toString();
    }

    protected String getWhereInSql(String fieldName, @SuppressWarnings("rawtypes") List list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("list参数不能为空.");
        }
        StringBuilder sql = new StringBuilder();
        sql.append(fieldName).append(" in (");
        for (Object obj : list) {
            String str = (String) obj;
            str = escapeSQLParam(str);
            sql.append("'" + str + "',");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

    protected String getLikeSQL(StatementParameter param) {
        StringBuilder whereSQL = new StringBuilder();
        for (Entry<String, String> entry : this.likeMap.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            if (whereSQL.length() > 0) {
                whereSQL.append(" and ");
            }
            whereSQL.append(fieldName).append(" like '%" + escapeSQLParam(value) + "%'");
        }

        return whereSQL.toString();
    }

    /**
     * 对SQL语句进行转义
     *
     * @param param SQL语句
     * @return 转义后的字符串
     */
    private static String escapeSQLParam(final String param) {
        int stringLength = param.length();
        StringBuilder buf = new StringBuilder((int) (stringLength * 1.1));
        for (int i = 0; i < stringLength; ++i) {
            char c = param.charAt(i);
            switch (c) {
                case 0: /* Must be escaped for 'mysql' */
                    buf.append('\\');
                    buf.append('0');
                    break;
                case '\n': /* Must be escaped for logs */
                    buf.append('\\');
                    buf.append('n');
                    break;
                case '\r':
                    buf.append('\\');
                    buf.append('r');
                    break;
                case '\\':
                    buf.append('\\');
                    buf.append('\\');
                    break;
                case '\'':
                    buf.append('\\');
                    buf.append('\'');
                    break;
                case '"': /* Better safe than sorry */
                    buf.append('\\');
                    buf.append('"');
                    break;
                case '\032': /* This gives problems on Win32 */
                    buf.append('\\');
                    buf.append('Z');
                    break;
                default:
                    buf.append(c);
            }
        }
        return buf.toString();
    }

    public <T> Paging<T> queryForPaging(Jdbc jdbc, Class<T> elementType) {
        StatementParameter param = new StatementParameter();
        StringBuilder sb = new StringBuilder();

        sb.append("select * from " + tableName);
        StringBuilder where = new StringBuilder();

        {
            String rangeSQL = this.getRangeSQL(param);
            if (rangeSQL.length() > 0) {
                where.append(rangeSQL);
            }
            {
                String whereSQL = this.getWhereSQL(param);
                if (whereSQL.length() > 0) {
                    if (where.length() > 0) {
                        where.append(" and ");
                    }
                    where.append(whereSQL);
                }
            }
            {
                String whereSQL = this.getWhereExpressionSQL();
                if (whereSQL.length() > 0) {
                    if (where.length() > 0) {
                        where.append(" and ");
                    }
                    where.append(whereSQL);
                }
            }
            {
                String whereSQL = this.getLikeSQL(param);
                if (whereSQL.length() > 0) {
                    if (where.length() > 0) {
                        where.append(" and ");
                    }
                    where.append(whereSQL);
                }
            }
        }

        if (where.length() > 0) {
            sb.append(" where " + where.toString());
        }
        // System.err.println("groupbyFieldName:" + groupbyFieldName + " orderFieldName:" + orderFieldName);
        if (groupbyFieldName != null && groupbyFieldName.length() > 0) {
            sb.append(" group by " + groupbyFieldName);
        }
        if (orderFieldName != null && orderFieldName.length() > 0) {
            sb.append(" order by " + orderFieldName + " " + orderDirection);
        }
        sb.append(" limit ?,?");
        param.setInt(limitStart);
        param.setInt(limitSize);

        String sql = sb.toString();
        // System.err.println("sql:" + sql);
        return jdbc.queryForPaging(sql, elementType, param);

    }

}
