package examsys.first.service;

import examsys.first.domain.Category;
import examsys.first.domain.TestPaper;
import examsys.first.pageParam.TestPaperParam;

public interface TestPaperService {
	/**
	 * 根据考试类型随机生成一张试卷
	 * @param category	考试类型
	 * @return
	 */
	public TestPaper generateTestPaper(Category category, String userId);
	
	/**
	 * 计算考试成绩并保存
	 * @param param
	 */
	public void saveTestScore(TestPaperParam param, String userId);
}
