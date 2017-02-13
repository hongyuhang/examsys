package examsys.first.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import examsys.first.domain.BaseInfo;

public class CommonUtils {
	private final static Logger logger = Logger.getLogger(CommonUtils.class);
	
	public static Map<String, Object> getJsonObj(boolean flag, BaseInfo data) {
		Map jsonOjb = new HashMap<String, Object>();
		jsonOjb.put("flag", flag);
		jsonOjb.put("data", data);
		return jsonOjb;
	}
	
	public static Map<String, Object> getJsonMapObj(boolean flag, Map<String, Object> data) {
		Map jsonOjb = new HashMap<String, Object>();
		jsonOjb.put("flag", flag);
		jsonOjb.put("data", data);
		return jsonOjb;
	}
	
	public static Map<String, Object> getJsonObjs(boolean flag, Collection data) {
		Map jsonOjb = new HashMap<String, Object>();
		jsonOjb.put("flag", flag);
		jsonOjb.put("data", data);
		return jsonOjb;
	}
	
	// 获得spring管理的Bean的实例
	public static Object getContextBean(String beanId) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(beanId);
	}
}
