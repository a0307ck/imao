package com.imao.attachment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imao.attachment.model.AttachmentInfo;
import com.imao.attachment.service.AttachmentInfoService;
import com.imao.code.Constants;
import com.imao.code.PageVo;
import com.imao.utils.WebResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "附件、图片上传公共接口")
@RestController
@RequestMapping("/attachment")
public class AttachmentInfoController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private AttachmentInfoService attachmentInfoService;

	@ApiOperation(value = "附件、图片上传公共接口 ", httpMethod = "POST", produces = "multipart/form-data", notes = "附件、图片上传公共接口  \n"
			+ "调用上传接口须向接口传参  relationId：关联表单id和整数attachmentAscription 附件所属模块")
	@PostMapping(value = "upload")
	@RequiresRoles(logical = Logical.OR, value = {"admin"})
	public WebResult uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
			AttachmentInfo attachmentInfo,HttpServletRequest request) {
		log.info("Enter Method of upload attachment .");
		WebResult rt = new WebResult();
		try {
			if (attachmentInfo != null) {
				if (attachmentInfo.getAttachmentAscription() == null || attachmentInfo.getAttachmentAscription() <= 0)
					return rt.setSuccess(false).setMsg("上传失败，参数attachmentAscription不能为空！");
				if (attachmentInfo.getAttachmentStatus() == null)
					return rt.setSuccess(false).setMsg("上传失败，参数attachmentStatus不能为空！");
				if (attachmentInfo.getAttachmentType() == null || attachmentInfo.getAttachmentType() <= 0)
					return rt.setSuccess(false).setMsg("上传失败，参数attachmentType不能为空！");
			} else {
				return rt.setSuccess(false).setMsg("上传失败，参数不能为空！");
			}
			
			String tokenHeader = request.getHeader(Constants.AUTH_TOKEN);
			if(StringUtils.isBlank(tokenHeader))return rt.setSuccess(false).setMsg("请先登录");
			AttachmentInfo attachment = attachmentInfoService.uploadFile(file, attachmentInfo,tokenHeader);
			if (attachment != null) {
				log.info("The method of update attachment success --> " + attachment.getAttachmentId());
				return rt.setSuccess(true).setMsg("上传成功").setReturnParm(attachment);
			} else {
				log.error("The method of update attachment error ");
				return rt.setSuccess(false).setMsg("上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("The method of update attachment has a exception -->" + e);
			return rt.setSuccess(false).setMsg("上传失败");
		}
	}
	
	@ApiOperation(value = "附件、图片上传公共接口(多个文件上传)", httpMethod = "POST", produces = "multipart/form-data", notes = "附件、图片上传公共接口(多个文件上传)  \n"
			+ "返回List附件实体")
	@PostMapping(value = "uploadMultiFiles")
	@RequiresRoles(logical = Logical.OR, value = {"admin"})
	public WebResult uploadMultiFiles(@RequestParam(value = "files", required = true) MultipartFile[] files,
			AttachmentInfo attachmentInfo,HttpServletRequest request) {
		log.info("Enter Method of upload attachment .");
		WebResult rt = new WebResult();
		try {
			if (attachmentInfo != null) {
				if (attachmentInfo.getAttachmentAscription() == null || attachmentInfo.getAttachmentAscription() <= 0)
					return rt.setSuccess(false).setMsg("上传失败，参数attachmentAscription不能为空！");
				if (attachmentInfo.getAttachmentStatus() == null)
					return rt.setSuccess(false).setMsg("上传失败，参数attachmentStatus不能为空！");
				if (attachmentInfo.getAttachmentType() == null || attachmentInfo.getAttachmentType() <= 0)
					return rt.setSuccess(false).setMsg("上传失败，参数attachmentType不能为空！");
			} else {
				return rt.setSuccess(false).setMsg("上传失败，参数不能为空！");
			}
			String token = request.getHeader(Constants.AUTH_TOKEN);
			if(StringUtils.isBlank(token))return rt.setSuccess(false).setMsg("请先登录");
			
			List<AttachmentInfo> attachmentList = attachmentInfoService.uploadMultiFiles(files, attachmentInfo,token);
			if (attachmentList != null && attachmentList.size()>0) {
				log.info("The method of update attachment success --> " + attachmentList.size());
				return rt.setSuccess(true).setMsg("上传成功").setReturnParm(attachmentList);
			} else {
				log.error("The method of update attachment error ");
				return rt.setSuccess(false).setMsg("上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("The method of update attachment has a exception -->" + e);
			return rt.setSuccess(false).setMsg("上传失败");
		}
	}


	
	@ApiOperation("公共的文件下载")
	@GetMapping(value = "genericDownloadFile")
	@RequiresRoles("admin")
	public void  GenericDownloadFile(AttachmentInfo attachmentInfo,HttpServletRequest request, HttpServletResponse response) {
		attachmentInfoService.genericDownloadFile(attachmentInfo,response);
		return ;
	}
	
	
	@ApiOperation("附件列表查询")
	@PostMapping(value = "findAttachmentsList")
	public WebResult attachmentList(AttachmentInfo attachmentInfo,PageVo page){
		WebResult rt = new WebResult();
		try {
			PageHelper.startPage(page.getPageNumber(), page.getPageSize());
			List<AttachmentInfo> attachmentList = attachmentInfoService.getListByAttachment(attachmentInfo);
			PageInfo<AttachmentInfo> pageInfo = new PageInfo<>(attachmentList);
			if (attachmentList != null && attachmentList.size()>0) {
				log.info("The method of find attachments success --> " + attachmentList.size());
				return rt.setSuccess(true).setMsg("查询成功").setReturnParm(pageInfo);
			} else {
				log.error("The method of find attachments and return null ");
				return rt.setSuccess(true).setMsg("列表为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("The method of find attachments has a exception -->" + e);
			return rt.setSuccess(false).setMsg("上传失败");
		}
	}
	


}
