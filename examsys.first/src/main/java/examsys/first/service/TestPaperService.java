package examsys.first.service;

import examsys.first.domain.Category;
import examsys.first.domain.TestPaper;

public interface TestPaperService {
	/**
	 * 根据考试类型随机生成一张试卷
	 * @param category	考试类型
	 * @return
	 */
	public TestPaper generateTestPaper(Category category);
}
