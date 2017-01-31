package examsys.first.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import examsys.first.dao.ItemMapper;
import examsys.first.domain.Category;
import examsys.first.domain.Item;
import examsys.first.domain.TestPaper;
import examsys.first.service.TestPaperService;

@Service
public class TestPaperServiceImpl implements TestPaperService {

	@Autowired
	private ItemMapper itemMapper;
	
	/**
	 * 根据考试类型随机生成一张试卷,并将userid作为key，放到缓存里
	 */
	@CachePut(value="examCache", key="#userId")
	public TestPaper generateTestPaper(Category category, String userId) {
		TestPaper paper = new TestPaper();
		paper.setTestContent(new HashMap<Integer, List<Item>>());
		if (category == null)
			return null;
		
		setTestPaperFromCategory(category, paper);
		
		if (category.getComposes() != null) {
			category.getComposes().forEach(categoryCompose -> {
				System.out.println(categoryCompose);
				paper.getTestContent().put(categoryCompose.getType(), itemMapper.getRandomItems(categoryCompose.getType(), categoryCompose.getCount()));
			});
		} else {
			return null;
		}
		return paper;
	}

	/**
	 * 根据选择的考试类型设置试卷信息
	 * @param category
	 * @param paper
	 */
	private void setTestPaperFromCategory(Category category, TestPaper paper) {
		paper.setCategoryName(category.getName());
		paper.setPassScore(category.getPassScore());
		paper.setTotalScore(category.getScore());
		paper.setTotalTime(category.getTime());
	}

}
