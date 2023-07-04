package com.wuqin.admin.dto;

import lombok.Data;

@Data
public class PageQuery<T> {
    private int page;
    private int limit;
    private T date;
}
