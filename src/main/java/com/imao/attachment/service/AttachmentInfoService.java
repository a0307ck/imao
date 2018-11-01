package com.imao.attachment.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.imao.attachment.model.AttachmentInfo;

public interface AttachmentInfoService {
	/**
	 * 上传文件，返还新增实体数据
	 * @param file 
	 * @param legalDocument 
	 * */
	AttachmentInfo uploadFile(MultipartFile file, AttachmentInfo attachmentInfo);

	/**
	 * 更新附件关联的实体id
	 * @param attachmentId 
	 * @param relationId 实体id
	 * */
	Boolean updateAttachmentRelationIdById(Long attachmentId, Long relationId);

	/**
	 * 上传多个文件，返还新增实体数据
	 * @param files
	 * @param legalDocument 
	 * */
	List<AttachmentInfo> uploadMultiFiles(MultipartFile[] files, AttachmentInfo attachmentInfo);

	void genericDownloadFile(AttachmentInfo attachmentInfo, HttpServletResponse response);

	List<AttachmentInfo> getListByAttachment(AttachmentInfo attachmentInfo);
}
