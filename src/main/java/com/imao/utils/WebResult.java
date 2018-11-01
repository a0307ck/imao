package com.imao.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @ClassName: WebResult
 * @Description: 后台接口返回消息通用实体类
 */
@ApiModel(description= "返回响应数据")
public class WebResult {
	@ApiModelProperty(value = "是否成功")
	private boolean success;
	 @ApiModelProperty(value = "返回对象")
	private Object returnParm;
	 @ApiModelProperty(value = "信息提示")
	private Object msg;
	 @ApiModelProperty(value = "状态码")
	private int status;
	public static final Logger logger = LoggerFactory.getLogger(WebResult.class);

	public boolean isSuccess() {
		return success;
	}

	public WebResult setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public WebResult setStatus(int status) {
		this.status = status;
		return this;
	}

	public Object getMsg() {
		return msg;
	}

	public WebResult setMsg(Object msg) {
		this.msg = msg;
		return this;
	}

	public Object getReturnParm() {
		return returnParm;
	}

	public WebResult setReturnParm(Object returnParm) {
		this.returnParm = returnParm;
		return this;
	}

}