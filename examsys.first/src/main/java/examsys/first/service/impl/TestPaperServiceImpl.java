package examsys.first.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import examsys.first.common.CommonUtils;
import examsys.first.dao.ItemMapper;
import examsys.first.domain.Category;
import examsys.first.domain.Item;
import examsys.first.domain.TestPaper;
import examsys.first.pageParam.TestPaperParam;
import examsys.first.service.TestPaperService;
import examsys.first.web.controller.TestPaperController;

@Service
public class TestPaperServiceImpl implements TestPaperService {
	private final static Logger logger = Logger.getLogger(TestPaperServiceImpl.class);
	
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

	/**
	 * 计算考试成绩并保存
	 * @param param
	 */
	@Async("asyncExecutor")
	public void calculateTestScoreAndSave(TestPaperParam param) {
		CacheManager cacheManager = (CacheManager)CommonUtils.getContextBean("cacheManager");
		Object cache = cacheManager.getCache("examCache");
		// 取出缓存
		
		// TODO:用缓存的数据和提交的试卷进行对比，判分
		logger.info(Thread.currentThread().getId());
		
	}

}
