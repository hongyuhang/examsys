package examsys.first.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import examsys.first.domain.BaseInfo;

public class CommonUtils {
	private final static Logger logger = Logger.getLogger(CommonUtils.class);
	
	public static Map<String, Object> getJsonObj(boolean flag, BaseInfo data) {
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
}
