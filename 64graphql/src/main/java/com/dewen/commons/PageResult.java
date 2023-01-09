package com.dewen.commons;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private List<T> items;
    private int pageNo;
    private int pageSize;
    private int totalCount;
}