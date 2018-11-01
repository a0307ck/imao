package com.imao.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 * <p>Title: DownloadUtil</p>
 * <p>Description: 文件下载类</p>
 * <p>Company: ds</p> 
 * @author jimmy
 * @date 2018年7月10日 下午1:28:08
 */
public class DownloadUtil {
/**
 * 
 * @param response
 * @param path :文件路径和文件名称
 */
	public static String downloadFile(HttpServletResponse response, String path) {
		InputStream fis=null;
		OutputStream fos=null;
		try {
			File file = new File(path);
			if(!file.exists()){
				return "1";
			}
			fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			String filename = file.getName();
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			fos = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			fos.write(buffer);
			fos.flush();
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "2";
		}
		finally
        {
            try
            {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
		return "0";
	}
	/**
	 * 
	 * @param response
	 * @param path :文件路径和文件名称
	 */
	public static String downloadFileWithFileName(HttpServletResponse response, String path,String downloadFileName) {
		InputStream fis=null;
		OutputStream fos=null;
		try {
			File file = new File(path);
			if(!file.exists()){
				return "1";
			}
			fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(downloadFileName.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			fos = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			fos.write(buffer);
			fos.flush();
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "2";
		}
		finally
		{
			try
			{
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return "0";
	}
	
	/**
	 * 断点下载
	 * @param path 文件路经+文件名
	 * @param offset 开始的字节
	 * @param response
	 * @param request
	 * @return 0:下载成功;1:下载失败-没有文件;2:下载失败-抛出异常
	 */
	public static String downloadFile(String path,Long offset,HttpServletResponse response,HttpServletRequest request) {
		InputStream fis=null;
		OutputStream fos=null;
		byte[] buffer = new byte[1024];
		try {
			File file = new File(path);
			if(!file.exists()){
				return "1";
			}
			String filename = file.getName();
			fis = new BufferedInputStream(new FileInputStream(path));
			if(StringUtils.isEmpty(offset)){
				offset=0L;
			}
			if (request.getHeader("Range") != null)
			{
				offset=Long.parseLong(request.getHeader("Range").replaceAll("bytes=", ""));
			}
			if(offset>0 && offset>file.length()){
				offset=file.length();
			}
			fis.skip(offset);
			response.reset();
			response.setHeader("Content-Range", "bytes=" + offset.toString() );
			response.addHeader("Content-Disposition","attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			fos = new BufferedOutputStream(response.getOutputStream());
			int i=0;
			while ((i = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, i);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return "2";
		}
		finally
        {
            try
            {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
		return "0";
	}
	
	/** 
     * 下载URL文件并保存到本地  
     * @param url :下载文件的网址
     * @param localFilePath 本地文件路径 
     */
    public static void downloadFile(String url, String localFilePath)
    {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try
        {
        	File f = new File(localFilePath);
            if(!f.exists()){
            	return;
            }
        	URL urlfile = new URL(url);
        	HttpURLConnection httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            byte[] b = new byte[2048];
            int len = 0;
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
            	if (bis != null) {
            		bis.close();
				}
				if (bos != null) {
					bos.close();
				}
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)  
    { 
    	
//    	//具体接口参数需参照自管理门户在线文档
//    	String access_number="1064964440334";  //物联网卡号(149或10649号段)
//    	String user_id = "RcB7EZNfAOz2u3n2aPTbzHQ416A4XKDx";     //用户名OPENAPI开户成功。账号：dsrgzn
//    	String password = "aX73B77W2s5Lae3U";    //密码
//    	String method = "queryPakage";  //接口名-套餐使用量查询
//    	String monthDate="20171201";
//    	String[] arr = {access_number,user_id,password,method}; //加密数组，数组所需参数根据对应的接口文档
//    	//yajB5mF9M
//      	//key值指密钥，由电信提供，每个客户都有对应的key值，key值平均分为三段如下：
//    	String key1 = "yaj";
//    	String key2 = "B5m";
//    	String key3 = "F9M";
//    	
//    //	DesUtils des = new DesUtils(); //加密工具类实例化
//		String passwordEnc = DesUtils.strEnc(password,key1,key2,key3);  //密码加密 
//		System.out.println("密码加密结果:"+passwordEnc);
//		//密码加密结果：441894168BD86A2CC
//		
//		String sign = DesUtils.strEnc(DesUtils.naturalOrdering(arr),key1,key2,key3); //生成sign加密值
//		System.out.println("sign加密结果:"+sign);
//		//sign加密结果：45E8B9924DE397A8F7E5764767810CF774CC7E1685BA702C9C4C367EFDAE5D932B37C0C8F0F8EB0CAD6372289F407CA941894168BD86A2CC32E5804EA05BAA5099649468B9418E52
//		
//		String passwordDec = DesUtils.strDec(passwordEnc,key1,key2,key3);//密码解密
//		System.out.println("密码解密结果:"+passwordDec);
//        //密码解密结果 :test
//		
//		String signDec = DesUtils.strDec(sign,key1,key2,key3); //sign解密
//		System.out.println("sign解密结果:"+signDec);
//		//sign解密结果：14914000000,queryPakage,test,test	
//		
//    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	String date=simpleDateFormat.format(new Date());
//    	Object object  = EhcacheUtil.getInstance().get("ehcacheRealTime", "dateKey");
//    	if(object==null)
//    	{
//    		System.out.println("ehcacheRealTime null");
//    		EhcacheUtil.getInstance().put("ehcacheRealTime", "dateKey", date);
//    		object  = EhcacheUtil.getInstance().get("ehcacheRealTime", "dateKey");
//    	}
//    	
//		if(object != null){
//			System.out.println(String.valueOf(object));
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(String.valueOf(simpleDateFormat.format(new Date())));
//			System.out.println(String.valueOf(object));
//		}
//    	/*int hashcode = "sss".hashCode();
//    	int dir1 = hashcode&0xf;  //0--15
//    	int dir2 = (hashcode&0xf0)>>4;  //0-15
//    	System.out.println(dir1);
//    	System.out.println(dir2);
//    	
//    	long idd = -100;
//    	System.out.println(idd);
//    	DownloadUtil downloadUtil=new DownloadUtil();
//    	MyThread thread=downloadUtil.new MyThread();
//    	MyThread thread1=downloadUtil.new MyThread();
//    	thread.start();
//    	thread1.start();*/
//       /* String url = "http://i1.sinaimg.cn/dy/deco/2013/0329/logo/LOGO_1x.png";                                      
//        String filePathName ="d:"+ url.substring(url.lastIndexOf("/"));   
//        downloadFile(url, filePathName);    
//        System.out.println(filePathName);  */
//        
//       /* Scanner in = new Scanner(System.in);
//        System.out.println("What's your name?");
//        String name = in.nextLine();
//        System.out.printf("Hello! %s!How old are you?",name);
//        int age = in.nextInt();
//        System.out.printf("So, you are %s years old.",age);*/
//        
//        CacheOperation cache= CacheOperation.getInstance();
//        cache.addCacheData("key", "zd");
//        Object obj=cache.getCacheData("key", 2L, 0);
//        try {
//			Thread.sleep(3);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        System.out.println(cache.getCacheData("key", 2L, 0));
//        System.out.println(obj.toString());
//        
    }  
     class  MyThread extends Thread{
        @Override
        public void run() {
        	synchronized (MyThread.class) {
        		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
            	String ctime = simpleDateFormat.format(new Date()); 
            	long los=System.currentTimeMillis();
            	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                System.out.println("线程"+Thread.currentThread().getName()+"-----"+ctime);
                System.out.println("线程"+Thread.currentThread().getName()+"-----"+String.valueOf(los));
                System.out.println("线程"+Thread.currentThread().getName()+"-----"+uuid);
			}
        	
        }
    }
}
