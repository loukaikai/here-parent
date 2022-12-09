package com.here.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ResultObject {
    private Object data;
    private String message;
    private Integer code;
}
