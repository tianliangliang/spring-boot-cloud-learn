package com.tll.sbcl.springbootlearn.chapter3;

import lombok.Data;

/**
 * Created by tll on 2017/11/2 13:17
 **/
@Data
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 100;
    private Integer code;
    private String message;
    private String url;
    private T data;
}
