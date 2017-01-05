package examsys.first.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Object getRndTestPaper(@PathVariable("id") String categoryCode, HttpServletRequest request) {
		Category category = categoryService.getCategory(categoryCode);
		
		if (category == null) {
			return CommonUtils.getJsonObj(false, null);
		}
		
		TestPaper paper = testPaperService.generateTestPaper(category);
		return CommonUtils.getJsonObj(false, paper);
	}
	
}
