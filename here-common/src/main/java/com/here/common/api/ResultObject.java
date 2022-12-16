package com.here.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class ResultObject implements Serializable {

    private Object data;
    private String message;
    private Integer code;
}

