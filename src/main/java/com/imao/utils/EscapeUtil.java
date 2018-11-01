package com.imao.utils;



/**
 * * <p>Title: EscapeUtil</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author jimmy
 * @date 2018年7月10日 下午1:28:08
 */
public class EscapeUtil {

	/*
	 * 模糊查询:把%,_转义一下再查。
	 */
	public static String mbReplace(String str) {
		if (str != null) {
			if(str.length()==0) return "";
			if (str.indexOf("_") >= 0) {
				str = str.replace("_", "\\_");
			}
			if (str.indexOf("%") >= 0) {
				str = str.replace("%", "\\%");
			}
		}
		return str;
	}
}
