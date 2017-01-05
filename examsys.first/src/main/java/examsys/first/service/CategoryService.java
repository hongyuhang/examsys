package examsys.first.service;

import examsys.first.domain.Category;

public interface CategoryService {
	
	/**
	 * 根据考试类型code获取一个完整的考试类型对象
	 * @param categoryCode
	 * @return
	 */
	public Category getCategory(String categoryCode);
}
