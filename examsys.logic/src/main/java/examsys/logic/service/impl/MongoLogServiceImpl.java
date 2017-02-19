package examsys.logic.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import examsys.base.service.ILogService;

@Service("logService")
public class MongoLogServiceImpl implements ILogService {
	private final static Logger logger = Logger.getLogger(MongoLogServiceImpl.class);
	
	@Autowired
	private MongoTemplate template;
	
	public void saveLog(Map<String, Object> logInfo) {
		try {
			logger.info("开始保存log:" + logInfo);
			template.save(logInfo, "historylog");
			logger.info("保存log结束");
		} catch(Exception e) {
			logger.error("出现了异常:", e);
		}
	}

}
