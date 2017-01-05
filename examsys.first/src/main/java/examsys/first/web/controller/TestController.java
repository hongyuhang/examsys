package examsys.first.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import examsys.first.service.CategoryService;

@Controller
public class TestController {

	@Autowired
	private CategoryService service;
	
	@RequestMapping(value = "/helloWorld")
	@ResponseBody
	public Object helloWorld() {
		service.getCategory("01");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "helloWorld");
		return map;
	}
}
