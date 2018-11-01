package com.imao.attachment.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.imao.attachment.model.AttachmentInfo;
@Repository
public interface AttachmentInfoMapper {
	void insertAttachmentInfo(AttachmentInfo attachmentInfo);

	AttachmentInfo selectByPrimaryKey(Long attachmentId);

	void updateByPrimaryKeySelective(AttachmentInfo attachment);

	AttachmentInfo selectById(AttachmentInfo attachment);

	List<AttachmentInfo> selectAll(AttachmentInfo attachmentInfo);
}