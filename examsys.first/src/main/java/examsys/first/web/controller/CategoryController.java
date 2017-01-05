package examsys.first.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examsys.first.common.CommonUtils;
import examsys.first.domain.Category;
import examsys.first.service.CategoryService;

@RestController
public class CategoryController {
	private final static Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/category/all")
	public Object getAllCategory(HttpServletRequest request) {
		List<Category> list = categoryService.getAllCatgory();
		
		return CommonUtils.getJsonObjs(false, list);
	}
}
