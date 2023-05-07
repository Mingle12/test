package com.Mingle.util;

import lombok.Data;

@Data
public class BaseQuery {
    private Integer page = 1;
    private Integer size = 10;
}
