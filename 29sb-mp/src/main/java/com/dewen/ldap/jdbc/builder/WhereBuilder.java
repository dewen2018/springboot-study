package com.dewen.ldap.jdbc.builder;

import com.dewen.ldap.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

public class WhereBuilder extends AbstractSqlBuilder implements SqlBuilder {
    // 操作符集合
    protected List<String> operatorList = new ArrayList<String>();
    // 自定义条件语句
    protected String customCondition = null;

    public void setOperator(String operator) {
        operatorList.add(operator);
    }

    public String getCustomCondition() {
        return customCondition;
    }

    public void setCustomCondition(String customCondition) {
        this.customCondition = customCondition;
    }

    /**
     * 拼接where查询条件.
     */
    @Override
    public String getSql() {
        if (fieldList.isEmpty() && StrUtil.isBlank(customCondition)) {
            throw new NullPointerException("还没有设置任何参数.");
        }
        StringBuilder sb = new StringBuilder("");
        if (fieldList.isEmpty()) {
            if(StrUtil.isNotBlank(customCondition)) {
                sb.append(customCondition);
            }
        } else {
            int count = 0;
            for (String filedName : fieldList) {
                if (count > 0) {
                    sb.append(" and ");
                }
                String operator = operatorList == null || count >= operatorList.size() || operatorList.get(count) == null ? "=" : operatorList.get(count);
                sb.append(filedName).append(" " + operator + " ");
                if(QueryBuilder.COMPOPERATOR_LIKE.equals(operator)) {
                    sb.append("concat('%',?,'%')");
                } else {
                    sb.append("?");
                }
                count++;
            }
            if(StrUtil.isNotBlank(customCondition)) {
                sb.append(" and ").append(customCondition);
            }
        }
        return sb.toString();
    }
}

