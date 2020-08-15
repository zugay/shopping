package com.gec.shopping.pojo;


/**
 * 响应信息的封装类
 * @author gec
 *
 */
public class RespBean {
	private boolean success;//是否成功

	private String message;//返回信息

	public RespBean() {
	}

	public RespBean(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
