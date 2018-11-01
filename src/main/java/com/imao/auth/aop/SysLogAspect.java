package com.imao.auth.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.imao.auth.model.SysLog;
import com.imao.auth.model.SysUser;
import com.imao.auth.service.SysLogService;
import com.imao.auth.service.SysUserService;
import com.imao.code.Constants;
import com.imao.utils.JWTUtil;


/**
 * 
* <p>Title: </p>
* <p>Description:log 切面</p>
* <p>Company: ds </p> 
* @date 2018年7月19日 上午10:48:59
 */

@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	public SysLogService sysLogService;
	@Autowired
	public SysUserService sysUserService;

	public static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);
	
	// Controller层切点
	@Pointcut("@annotation(com.imao.auth.aop.SysLogControllerAs)")
	public void controllerAspect() {
	}
	
	// Controller层切点
	@Pointcut("@annotation(com.imao.auth.aop.SysLogServiceAs)")
	public void serviceAspect() {
	}
	
	@After("controllerAspect()")
	public void doAfter(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		// 如果Controller没有操作数据库则通过设置该属性，跳过下面的操作
		if(request.getAttribute("NotSaveLog") !=null){
			request.removeAttribute("NotSaveLog");
			return;
		}
		String tokenHeader = null;
		//登录
		String requestUrl = request.getServletPath();
		if(StringUtils.isNotBlank(requestUrl)&&requestUrl.contains("/login")){
			tokenHeader = (String) request.getAttribute("token");
			logger.debug("url--->"+requestUrl+"--token-->"+tokenHeader);
		}else{
			tokenHeader = request.getHeader(Constants.AUTH_TOKEN);//从头部获取JWT字符串信息
		}
		
	        if(logger.isDebugEnabled()) {
	            logger.debug("=========>tokenHeader {}", tokenHeader);    
	        }
//		SysUser SessionUser = SysPropUtils.getUser();
		
		String userName = JWTUtil.getUsername(tokenHeader);
		SysUser SessionUser = null;
		
		try {
			if(StringUtils.isEmpty(userName)){
				logger.error("无效的Token值："+tokenHeader);
			}else{
				SessionUser = sysUserService.findByUserName(userName);
				//获得日志信息并保存到数据库
				if(SessionUser != null && SessionUser.getUserName() != null){
					sysLogService.saveLog(getSysLog(request,SessionUser,getControllerMethodDescription(joinPoint),sysLogService));
				}
			}
		} catch (Exception e) {
			//异常日志信息
			logger.error("请求信息:"+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
			logger.error("请求人信息:"+SessionUser.getUserName());
		}
	}
	
	@After("serviceAspect()")
	public void doAfterV2(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		// 如果Controller没有操作数据库则通过设置该属性，跳过下面的操作
		if(request.getAttribute("NotSaveLog") !=null){
			request.removeAttribute("NotSaveLog");
			return;
		}
		String tokenHeader = request.getHeader(Constants.AUTH_TOKEN);//从头部获取JWT字符串信息
        if(logger.isDebugEnabled()) {
            logger.debug("=========>tokenHeader {}", tokenHeader);    
        }
        String userName = JWTUtil.getUsername(tokenHeader);
		SysUser SessionUser = null;
		
		try {
			if(StringUtils.isEmpty(userName)){
				logger.error("无效的Token值："+tokenHeader);
			}else{
				SessionUser = sysUserService.findByUserName(userName);
				//获得日志信息并保存到数据库
				if(SessionUser != null && SessionUser.getUserName() != null){
					sysLogService.saveLog(getSysLog(request,SessionUser,getServiceMethodDescription(joinPoint),sysLogService));
				}
			}
		} catch (Exception e) {
			//异常日志信息
			logger.error("请求信息:"+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+"()");
			logger.error("请求人信息:"+SessionUser.getUserName());
		}
	}
	
	/**
	 * 特殊情况处理，用于用户退出
	 * @param s
	 * @param sysLogService
	 */
	public void logout(String module,String description,SysLogService sysLogService){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenHeader = request.getHeader(Constants.AUTH_TOKEN);//从头部获取JWT字符串信息
        if(logger.isDebugEnabled()) {
            logger.debug("=========>tokenHeader {}", tokenHeader);    
        }
        String userName = JWTUtil.getUsername(tokenHeader);
        SysUser SessionUser = null;
        if(StringUtils.isEmpty(userName)){
			logger.error("无效的Token值："+tokenHeader);
		}else{
			SessionUser = sysUserService.findByUserName(userName);
		}
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(description);
		arr.add(module);
		if(SessionUser != null && SessionUser.getUserName() != null){
//			SessionUser.setContentChange(arr.get(0)+":"+SessionUser.getUserName());
			sysLogService.saveLog(getSysLog(request,SessionUser,arr,sysLogService));
		}
	}
	
	/**
	 * 特殊情况处理，用于导入
	 * @param s
	 * @param sysLogService
	 */
	public void importExcel(String module,String description,String s,SysLogService sysLogService){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String tokenHeader = request.getHeader(Constants.AUTH_TOKEN);//从头部获取JWT字符串信息
        if(logger.isDebugEnabled()) {
            logger.debug("=========>tokenHeader {}", tokenHeader);    
        }
        String userName = JWTUtil.getUsername(tokenHeader);
        SysUser SessionUser = null;
        if(StringUtils.isEmpty(userName)){
			logger.error("无效的Token值："+tokenHeader);
		}else{
			SessionUser = sysUserService.findByUserName(userName);
		}
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(description);
		arr.add(module);
		if(SessionUser != null && SessionUser.getUserName() != null){
			sysLogService.saveLog(getSysLog(request,SessionUser,arr,sysLogService));
		}
	}
	
	// 获取IP
	private static String getIpAddress(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1")? "127.0.0.1" : ip ;
	}
	
	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static ArrayList<String> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		String module = "";
		ArrayList<String> arr = new ArrayList<String>();
		if(methods != null){
			for (Method method : methods) {
				if(methodName.equals(method.getName())){
					Class[] classes = method.getParameterTypes();
					if(classes.length == args.length){
						description = method.getAnnotation(SysLogControllerAs.class).description();
						module = method.getAnnotation(SysLogControllerAs.class).module();
						arr.add(description);
						arr.add(module);
						break;
					}
				}
			}
		}
		return arr;
	}
	
	/**
	 * 获取注解中对方法的描述信息 用于Service层注解
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static ArrayList<String> getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		String module = "";
		ArrayList<String> arr = new ArrayList<String>();
		if(methods != null){
			for (Method method : methods) {
				if(methodName.equals(method.getName())){
					Class[] classes = method.getParameterTypes();
					if(classes.length == args.length){
						description = method.getAnnotation(SysLogServiceAs.class).description();
						module = method.getAnnotation(SysLogServiceAs.class).module();
						arr.add(description);
						arr.add(module);
						break;
					}
				}
			}
		}
		return arr;
	}
	
	/**
	 * 添加日志
	 * @param request
	 * @param sessionUser
	 * @param description
	 * @param sysLogService2
	 * @return
	 */
	private static SysLog getSysLog(HttpServletRequest request, SysUser sessionUser,
			ArrayList<String> arr, SysLogService sysLogService) {
		if(org.springframework.util.StringUtils.isEmpty(sessionUser)){
			return null;
		}
		SysLog syslog = new SysLog();
//		syslog.setPermission(sysLogService.getPermission(sessionUser.getRoleId()).toString());
//		syslog.setSysRole(sessionUser.getRoleId().toString());
		if(sessionUser.getStatus() == Constants.MODEL_STATUS_BAN){//2为禁用  1为正常
			syslog.setStatus(Constants.MODEL_STATUS_BAN);
		}else{
			syslog.setStatus(Constants.MODEL_STATUS_USE);
		}
//		if (sessionUser.getContentChange() == null || sessionUser.getContentChange().equals("")) {
//			syslog.setContentChange("");
//			sessionUser.setContentChange(null);
//		}else if(sessionUser.getContentChange() != null) {
//			syslog.setContentChange(sessionUser.getContentChange());
//			sessionUser.setContentChange(null);
//		}
		
		String agent = request.getHeader("user-agent");
		syslog.setBrowerType(agent);
		syslog.setCodeSys(RandomStringUtils.randomNumeric(6));
		String ip = getIpAddress(request);
		syslog.setLoginIp(ip);
		syslog.setLoginTime(new Date());
		syslog.setUserName(sessionUser.getUserName());
		syslog.setContect(arr.get(0));
		syslog.setModule(arr.get(1));
		return syslog;
	}

	
}
