package examsys.first.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examsys.first.dao.CategoryMapper;
import examsys.first.domain.Category;
import examsys.first.service.CategoryService;

@Service
public class CateoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryMapper mapper; 

	/**
	 * 根据考试类型code获取一个完整的考试类型对象
	 * @param categoryCode
	 * @return
	 */
	public Category getCategory(String categoryCode) {
		return mapper.selectByPrimaryKey(categoryCode);
	}

}
