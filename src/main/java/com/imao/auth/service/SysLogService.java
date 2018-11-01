package com.imao.auth.service;
import com.imao.auth.model.SysLog;
import com.imao.code.Service;


/**
 * Created by ChineseKam on 2018/10/31.
 */
public interface SysLogService extends Service<SysLog> {

	void saveLog(SysLog sysLog);

}
