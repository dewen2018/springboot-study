package com.dewen.ldap;

import java.util.Date;

public class Query {

    /**
     * 日期字段
     */
    private DateColumnFilter dateFilter;

    /**
     * 按字段过滤
     */
    private ColumnFilter filter;

    public DateColumnFilter getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(DateColumnFilter dateFilter) {
        this.dateFilter = dateFilter;
    }

    public ColumnFilter getFilter() {
        return filter;
    }

    public void setFilter(ColumnFilter filter) {
        this.filter = filter;
    }

    public static class DateColumnFilter {
        /**
         * 字段名
         */
        private String name;
        /**
         * 开始时间
         */
        private Date startDate;
        /**
         * 结束时间
         */
        private Date endDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }

    public static class ColumnFilter {
        /**
         * 名称
         */
        private String name;

        /**
         * 值
         */
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

}
