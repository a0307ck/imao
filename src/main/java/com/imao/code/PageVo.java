package com.imao.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
* <p>Title: </p>
* <p>Description:</p>
 */
@ApiModel(description= "分页公共类")
public class PageVo {
	
	
	/** 删除标记（0：正常；1：删除）*/
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";

	/**
	 * 当前页
	 */
	 
	private Integer pageNumber = 1;
	
	/**
	 * 每页显示数据
	 */
	 
	private Integer pageSize = 10;
	
	/**
	 * 开始查询数
	 */
	@ApiModelProperty(hidden=true)
	private Integer startRow = 0;
	
	/**
	 * 显示页码数量
	 */
	@ApiModelProperty(hidden=true)
	private Integer page = 8;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		this.startRow = (pageNumber-1)*pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.startRow = (pageNumber-1)*pageSize;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
