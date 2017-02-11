package examsys.first.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
		request.getSession(true).setAttribute("userId", "ABCDE");
		// 因为暂时没有用户ID，所以用sessionid来代替userid
		TestPaper paper = testPaperService.generateTestPaper(category, request.getRequestedSessionId());
		request.getSession(true).setAttribute("testPaper", paper);
		return CommonUtils.getJsonObj(true, paper);
	}
	
	// 可以考虑在service层用spring异步处理来实现考试成绩的计算逻辑
	@RequestMapping(value="/testpaper/complete")
	public Object submitTestPaper(@RequestBody TestPaperParam param, HttpServletRequest request) {
		logger.info(param);
		logger.info(Thread.currentThread().getId());
		
		// 异步调用服务，计算考试成绩
		testPaperService.saveTestScore(param, request.getRequestedSessionId());
		
		// 从缓存里取出试题
		return CommonUtils.getJsonObj(true, null);
	}
}
