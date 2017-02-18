package examsys.first.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examsys.first.common.CommonUtils;
import examsys.first.domain.Category;
import examsys.first.domain.TestPaper;
import examsys.first.pageParam.TestPaperParam;
import examsys.first.service.CategoryService;
import examsys.first.service.TestPaperService;

@RestController
public class TestPaperController {
	private final static Logger logger = Logger.getLogger(TestPaperController.class);
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	TestPaperService testPaperService;
	
	@RequestMapping(value="/testpaper/{categoryCode}")
	public Object getRndTestPaper(@PathVariable("categoryCode") String categoryCode, HttpServletRequest request) {
		// TODO:应该加入防止刷新页面延长考试时间的逻辑
		Category category = categoryService.getCategory(categoryCode);
//		request.getRequestedSessionId();
		if (category == null) {
			logger.warn("考试类别ID为NULL");
			return CommonUtils.getJsonObj(false, null);
		}
		String userId = getUserId();
		request.getSession(true).setAttribute("userId", userId);
		// TODO:因为暂时没有用户ID，所以用sessionid来代替userid
		TestPaper paper = testPaperService.generateTestPaper(category, userId);
		request.getSession(true).setAttribute("testPaper", paper);
		return CommonUtils.getJsonObj(true, paper);
	}
	
	// 可以考虑在service层用spring异步处理来实现考试成绩的计算逻辑
	@RequestMapping(value="/testpaper/complete")
	public Object submitTestPaper(@RequestBody TestPaperParam param, HttpServletRequest request) {
		logger.info(param);
		logger.info(Thread.currentThread().getId());
		String userId = (String)request.getSession(true).getAttribute("userId");
		// 异步调用服务，计算考试成绩
		testPaperService.saveTestScore(param, userId);
		
		// 取出缓存
		CacheManager cacheManager = (CacheManager)CommonUtils.getContextBean("cacheManager");
		TestPaper cache = (TestPaper)cacheManager.getCache("examCache").get(userId).get();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryCode", cache.getCode());
		map.put("userId", userId);
		
		// 从缓存里取出试题
		return CommonUtils.getJsonMapObj(true, map);
	}
	
	/**
	 * 根据考试类别和用户id取得考试成绩
	 * @param categoryCode
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/testpapergrade/{categoryCode}/{userId}")
	public Object getGrade(@PathVariable("categoryCode") String categoryCode, @PathVariable("userId") String userId) {
		TestPaper testPaper = testPaperService.getGrade(categoryCode, userId);
		return CommonUtils.getJsonObj(true, testPaper);
	}
	
	/**
	 * 模拟一个随机的用户id
	 * @return
	 */
	private String getUserId() {
		String [] generator = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
							   "a","b","c","d","e","f","g","h","i","j","k","l","m","n",
							   "0","1","2","3","4","5","6","7","8","9"};
		Random rdm = new Random();
		String userId = "";
		for(int i = 0; i < 15; i++) {
			userId = userId + generator[rdm.nextInt(37)];
		}
		return userId;
	}
}
