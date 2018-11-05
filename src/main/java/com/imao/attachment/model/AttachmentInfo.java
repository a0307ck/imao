package com.imao.attachment.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("附件、图片上传共用接口")
public class AttachmentInfo {
    /**
     * 附件id
     */
	@ApiModelProperty(value="id主键")
    private Long attachmentId;

    /**
     * 原文件名
     */
	@ApiModelProperty(value = "原文件名", name = "fileName")
    private String fileName;

    /**
     * 数据库文件名
     */
	@ApiModelProperty(value = "数据库文件名", name = "fileDbName")
    private String fileDbName;

    /**
     * 附件类型（必填）
     */
	@ApiModelProperty(value = "附件类型", name = "attachmentType")
    private Integer attachmentType;

    /**
     * 附件所属模块:（必填）
     */
	@ApiModelProperty(value = "附件所属模块", name = "attachmentAscription")
    private Integer attachmentAscription;

    /**
     * 附件状态: 0无效,1有效（必填）
     */
	@ApiModelProperty(value = "附件状态: 0无效,1有效", name = "attachmentStatus")
    private Integer attachmentStatus;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    /**
     * 创建用户id
     */
	@ApiModelProperty(value = "创建用户id", name = "createUserId")
    private String createUserId;

    /**
     * 创建用户名称
     */
	@ApiModelProperty(value = "创建用户名称", name = "createUserName")
    private String createUserName;

    /**
     * 创建公司id
     */
	@ApiModelProperty(value = "创建公司id", name = "createCompanyId")
    private String createCompanyId;

    /**
     * 创建公司名称
     */
	@ApiModelProperty(value = "创建公司名称", name = "createCompanyName")
    private String createCompanyName;
    /**
     * 关联id
     */
	@ApiModelProperty(value = "关联id", name = "relationId")
    private Long relationId;
    
	public Long getRelationId() {
		return relationId;
	}


	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}


	/**
     * 获取附件id
     *
     * @return attachment_id - 附件id
     */
    
    public Long getAttachmentId() {
        return attachmentId;
    }


	/**
     * 设置附件id
     *
     * @param attachmentId 附件id
     */
    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    /**
     * 获取原文件名
     *
     * @return file_name - 原文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置原文件名
     *
     * @param fileName 原文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取数据库文件名
     *
     * @return file_db_name - 数据库文件名
     */
    public String getFileDbName() {
        return fileDbName;
    }

    /**
     * 设置数据库文件名
     *
     * @param fileDbName 数据库文件名
     */
    public void setFileDbName(String fileDbName) {
        this.fileDbName = fileDbName;
    }

    /**
     * 获取附件类型:（必填）
     *
     * @return attachment_type - 附件类型
     */
    public Integer getAttachmentType() {
        return attachmentType;
    }

    /**
     * 设置附件类型:（必填）
     *
     * @param attachmentType 附件类型
     */
    public void setAttachmentType(Integer attachmentType) {
        this.attachmentType = attachmentType;
    }

    /**
     * 获取附件所属模块:
     *
     * @return attachment_ascription - 附件所属模块:
     */
    public Integer getAttachmentAscription() {
        return attachmentAscription;
    }

    /**
     * 设置附件所属模块:
     *
     * @param attachmentAscription 附件所属模块:
     */
    public void setAttachmentAscription(Integer attachmentAscription) {
        this.attachmentAscription = attachmentAscription;
    }

    /**
     * 获取附件状态: 0无效,1有效
     *
     * @return attachment_status - 附件状态: 0无效,1有效
     */
    public Integer getAttachmentStatus() {
        return attachmentStatus;
    }

    /**
     * 设置附件状态: 0无效,1有效
     *
     * @param attachmentStatus 附件状态: 0无效,1有效
     */
    public void setAttachmentStatus(Integer attachmentStatus) {
        this.attachmentStatus = attachmentStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建用户id
     *
     * @return create_user_id - 创建用户id
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建用户id
     *
     * @param createUserId 创建用户id
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建用户名称
     *
     * @return create_user_name - 创建用户名称
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 设置创建用户名称
     *
     * @param createUserName 创建用户名称
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * 获取创建公司id
     *
     * @return create_company_id - 创建公司id
     */
    public String getCreateCompanyId() {
        return createCompanyId;
    }

    /**
     * 设置创建公司id
     *
     * @param createCompanyId 创建公司id
     */
    public void setCreateCompanyId(String createCompanyId) {
        this.createCompanyId = createCompanyId;
    }

    /**
     * 获取创建公司名称
     *
     * @return create_company_name - 创建公司名称
     */
    public String getCreateCompanyName() {
        return createCompanyName;
    }

    /**
     * 设置创建公司名称
     *
     * @param createCompanyName 创建公司名称
     */
    public void setCreateCompanyName(String createCompanyName) {
        this.createCompanyName = createCompanyName;
    }
}