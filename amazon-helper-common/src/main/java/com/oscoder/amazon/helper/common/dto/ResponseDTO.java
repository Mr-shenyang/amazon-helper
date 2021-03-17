package com.oscoder.amazon.helper.common.dto;

import java.io.Serializable;

public class ResponseDTO <T>{
    private static final Integer SUCCESS_CODE = 200;
    private static final Integer FAIL_CODE = 500;

    private Integer code;
    private T data;
    private String failMsg;

    private ResponseDTO(Integer code, T data, String failMsg) {
        this.code = code;
        this.data = data;
        this.failMsg = failMsg;
    }

    public static <T> ResponseDTO<T> success(T data){
        return new ResponseDTO<T>(SUCCESS_CODE,data,null);
    }

    public static <T> ResponseDTO<T> fail(String failMsg){
        return new ResponseDTO<T>(SUCCESS_CODE,null,failMsg);
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getFailMsg() {
        return failMsg;
    }

    public boolean isSuccess(){
        return SUCCESS_CODE.equals(this.getCode());
    }
}
