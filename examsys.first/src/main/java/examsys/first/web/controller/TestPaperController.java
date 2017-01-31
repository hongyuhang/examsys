package examsys.first.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examsys.first.common.CommonUtils;
import examsys.first.domain.Category;
import examsys.first.domain.TestPaper;
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
		// 因为暂时没有用户ID，所以用sessionid来代替userid
		TestPaper paper = testPaperService.generateTestPaper(category, request.getRequestedSessionId());
		request.getSession(true).setAttribute("testPaper", paper);
		return CommonUtils.getJsonObj(true, paper);
	}
	
	// 可以考虑用spring异步处理来实现考试成绩的计算逻辑
	@RequestMapping(value="/testpaper/complete")
	public Object submitTestPaper(HttpServletRequest request) {
		CacheManager cacheManager = (CacheManager)CommonUtils.getContextBean("cacheManager");
		Object cache = cacheManager.getCache("examCache");
		logger.info(cache);
		// 从缓存里取出试题
		return cache;
	}
}
