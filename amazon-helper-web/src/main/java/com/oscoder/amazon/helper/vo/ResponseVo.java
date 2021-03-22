package com.oscoder.amazon.helper.vo;

import lombok.ToString;

/**
 * @author
 * @create 2021-02-20 11:51 PM
 **/
@ToString
public class ResponseVo<T> {
	private static final int SUCCESS_CODE = 200;
	private static final int FAIL_CODE = 500;

	private static final int UNAUTHORIZED_CODE = 401;
	
	private Integer code;
	private String failMsg;
	private T data;
	
	public ResponseVo(Integer code, T data,String failMsg) {
		this.code = code;
		this.failMsg = failMsg;
		this.data = data;
	}
	
	public static <T> ResponseVo<T> success(T data){
		return new ResponseVo<>(SUCCESS_CODE,data,null);
	}

	public static <T> ResponseVo<T> failWithCode(Integer code,String failMsg){
		return new ResponseVo<>(code,null,failMsg);
	}

	public static <T> ResponseVo<T> fail(String failMsg){
		return new ResponseVo<>(FAIL_CODE,null,failMsg);
	}

	public static <T> ResponseVo<T> unauthorized(String failMsg){
		return new ResponseVo<>(UNAUTHORIZED_CODE,null,failMsg);
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getFailMsg() {
		return failMsg;
	}
	
	public T getData() {
		return data;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}
