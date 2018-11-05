package com.imao.attachment.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.imao.attachment.mapper.AttachmentInfoMapper;
import com.imao.attachment.model.AttachmentInfo;
import com.imao.attachment.service.AttachmentInfoService;
import com.imao.utils.DownloadUtil;
import com.imao.utils.JWTUtil;
import com.imao.utils.RandomStringUtils;
import com.imao.utils.TimeUtils;

@Service
@Transactional
public class AttachmentInfoServiceImpl implements AttachmentInfoService {
	public static Logger log = LoggerFactory.getLogger(AttachmentInfoServiceImpl.class);
	@Autowired
	private AttachmentInfoMapper attachmentMapper;
	
	@Value("${UploadPath.legalDocument}")
	private String legalDocumentUploadPath;

	@Override
	public AttachmentInfo uploadFile(MultipartFile file,AttachmentInfo attachmentInfo,String token) {
		try {
			// 原文件名
			String fileName = file.getOriginalFilename();
			String hzm = RandomStringUtils.randomAlphanumeric(5) + fileName.substring(fileName.lastIndexOf("."), fileName.length());
			// 数据库文件名
			String dbFileName = TimeUtils.dateFormat(new Date(), "yyyyMMddHHmmss") + hzm;
			// 解决乱码问题
			// String fName = new String(fileName.getBytes("iso-8859-1"),"UTF-8" );
			File targetFile = new File(legalDocumentUploadPath + File.separator + dbFileName);
			log.info("file -->" + legalDocumentUploadPath + "-->" + fileName);
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdir();
			}
			file.transferTo(targetFile);
			log.info(" upload attachment success " + legalDocumentUploadPath + File.separator + dbFileName);
			String userName = JWTUtil.getUsername(token);
			AttachmentInfo attachment = new AttachmentInfo();
			attachment.setAttachmentAscription(attachmentInfo.getAttachmentAscription());
			attachment.setAttachmentStatus(attachmentInfo.getAttachmentStatus());
			attachment.setAttachmentType(attachmentInfo.getAttachmentType());
			attachment.setCreateUserName(userName);
			attachment.setFileName(fileName);
			attachment.setFileDbName(dbFileName);
			attachment.setCreateTime(new Date());
			attachmentMapper.insertAttachmentInfo(attachment);
			log.info(" add attachment success and id is " + attachment.getAttachmentId());
			return attachment;
		} catch (Exception e) {
			log.error(" upload or add attachment  has a exception --> " + e);
			return null;
		}
	}
	
	@Override
	public Boolean updateAttachmentRelationIdById(Long attachmentId,Long relationId){
		try {
			AttachmentInfo at = new AttachmentInfo();
			at.setAttachmentId(attachmentId);
			AttachmentInfo attachment = attachmentMapper.selectById(at);
			attachment.setRelationId(relationId);
			attachmentMapper.updateByPrimaryKeySelective(attachment);
			log.info("update attachment relationId ("+relationId+") by attachmentId ("+attachmentId+") success .");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("update attachment relationId ("+relationId+") by attachmentId ("+attachmentId+") has a exception -->"+e);
			return false;
		}
	}

	@Override
	public List<AttachmentInfo> uploadMultiFiles(MultipartFile[] files, AttachmentInfo attachmentInfo,String token) {
		List<AttachmentInfo> attachmentList = new ArrayList<>();
		if(files!=null&&files.length>0){  
			for(MultipartFile file : files){  
				AttachmentInfo attach = uploadFile(file,attachmentInfo,token);
				if(attach!=null)attachmentList.add(attach);
			}  
		} 
		return attachmentList;
	}

	@Override
	public void genericDownloadFile(AttachmentInfo attachmentInfo, HttpServletResponse response) {
		try {
			AttachmentInfo ld = attachmentMapper.selectById(attachmentInfo);
			String filename=legalDocumentUploadPath+File.separator+ld.getFileDbName();
			String status = DownloadUtil.downloadFileWithFileName(response, filename,ld.getFileName());
			log.info("download "+filename+" flag is --> "+status);
			if("0".equals(status))
			log.info("download attachment success");
		} catch (Exception e) {
			log.error("download attachment error"+e);
			e.printStackTrace();
		}
	}

	@Override
	public List<AttachmentInfo> getListByAttachment(AttachmentInfo attachmentInfo) {
		return attachmentMapper.selectAll(attachmentInfo);
	}
}
