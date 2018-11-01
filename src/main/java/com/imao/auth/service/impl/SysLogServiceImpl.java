package com.imao.auth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imao.auth.model.SysLog;
import com.imao.auth.service.SysLogService;
import com.imao.code.AbstractService;


/**
 * Created by ChineseKam on 2018/10/31.
 */
@Service
@Transactional
public class SysLogServiceImpl extends AbstractService<SysLog> implements SysLogService {
//    @Autowired
//    private SysLogMapper sysLogMapper;

	@Override
	public void saveLog(SysLog sysLog) {
		this.save(sysLog);
	}

}
