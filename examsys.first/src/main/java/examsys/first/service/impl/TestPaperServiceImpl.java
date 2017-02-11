package examsys.first.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import examsys.first.common.CommonUtils;
import examsys.first.dao.AnswerMapper;
import examsys.first.dao.ItemMapper;
import examsys.first.dao.QuestionMapper;
import examsys.first.dao.TestPaperMapper;
import examsys.first.domain.Answer;
import examsys.first.domain.Category;
import examsys.first.domain.CategoryCompose;
import examsys.first.domain.Item;
import examsys.first.domain.Option;
import examsys.first.domain.Question;
import examsys.first.domain.TestPaper;
import examsys.first.pageParam.QuestionParam;
import examsys.first.pageParam.TestPaperParam;
import examsys.first.service.TestPaperService;

@Service
public class TestPaperServiceImpl implements TestPaperService {
	private final static Logger logger = Logger.getLogger(TestPaperServiceImpl.class);
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private TestPaperMapper testPaperMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private AnswerMapper answerMapper;
	
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
		paper.setCode(category.getCode());
		int itemCount = 0;
		for (CategoryCompose categoryCompose : category.getComposes()) {
			itemCount = itemCount + categoryCompose.getCount();
		}
		paper.setItemCount(itemCount);
	}

	/**
	 * 计算考试成绩并保存
	 * @param param
	 */
	@Async("asyncExecutor")
	public void saveTestScore(TestPaperParam param, String userId) {
		// TODO:直接把考试内容保存进mongodb中
		
		// 取出缓存
		CacheManager cacheManager = (CacheManager)CommonUtils.getContextBean("cacheManager");
		TestPaper cache = (TestPaper)cacheManager.getCache("examCache").get(userId).get();
		// TODO:用缓存的数据和提交的试卷进行对比，判分
		List<Question> questions = new ArrayList<Question>();
		int finalScore = 0;
		// TODO:写的不好，应该把形成最终结果的逻辑和计算分数的逻辑分开
		for(QuestionParam qParam : param.getQuestions()) {
			Question question = new Question();
			question.setSeq(qParam.getIndex());
			question.setQuestionId(Long.parseLong(qParam.getQuestionCode()));
			
			// 根据考题类型取得缓存中的正确答案，和回答进行对比
			List<String> answers = new ArrayList<String>();
			int[] tempScore = new int[1];
			logger.info(cache.getTestContent().get(qParam.getQuestionType()));
			for(Item item : cache.getTestContent().get(Integer.parseInt(qParam.getQuestionType()))) {
				if (item.getItemId() ==Long.parseLong(qParam.getQuestionCode())) {
					question.setQuestionContent(item.getItemContont());
					question.setCorrectFlag(0);
					
					List<Answer> answerList = new ArrayList<Answer>();
					
					if (qParam.getSelected() == null || qParam.getSelected().isEmpty()) {
						questions.add(question);
					}
					

					for(Option option : item.getOptions()) {
						Answer answer = new Answer();
						answer.setAnswerContent(option.getOptionContent());
						answer.setCorrectFlag(option.getCorrectFlag());
						// 非判断题的情况
						if (!qParam.getQuestionType().equals("3")) {
							if (qParam.getSelected() != null && qParam.getSelected().contains(option.getOptionId() + "")) {
								answer.setSelected(1);
							} else {
								answer.setSelected(0);
							}
						} else {
							// 判断题的情况
							if (qParam.getSelected() != null && qParam.getSelected().size() > 0) {
								answer.setSelected(Integer.parseInt(qParam.getSelected().get(0)));
							}
						}
						answerList.add(answer);

						// 非判断题的情况
						if (!qParam.getQuestionType().equals("3")) {
							if (option.getCorrectFlag() == 1) {
								answers.add(option.getOptionId() + "");
								tempScore[0] = item.getScore();
							}
						} else {
							// 判断题的情况
							answers.add(option.getCorrectFlag()+ "");
							tempScore[0] = item.getScore();
						}
					}
					question.setAnswers(answerList);
					
					if (qParam.getSelected() == null || qParam.getSelected().isEmpty()) {
						break;
					}
					// 非判断题的情况
					if (answers.containsAll(qParam.getSelected())) {
						finalScore = finalScore + tempScore[0];
						question.setCorrectFlag(1);
					}

					questions.add(question);
					break;
				}
			}
			
			
		}
		
		TestPaper testPaper = createTestPaper(param, userId, cache, finalScore);
		testPaperMapper.insertSelective(testPaper);
		long testPaperId = testPaperMapper.getIdByUserIdAndCName(userId, cache.getCategoryName());
		
		for(Question question : questions) {
			question.setTestpaperId(testPaperId);
			questionMapper.insertSelective(question);
			Long questionId = questionMapper.getIdByTestPaperIdAndSeq(testPaperId, question.getSeq());
			for(Answer answer : question.getAnswers()) {
				answer.setQuestionId(questionId);
				answerMapper.insertSelective(answer);
			}
		}
		
		logger.info(testPaperId);
		logger.info(testPaper);
		logger.info(questions);
		logger.info(Thread.currentThread().getId());
		
	}

	/**
	 * 生成TestPaper对象
	 * @param param
	 * @param userId
	 * @param cache
	 * @param finalScore
	 * @return
	 */
	private TestPaper createTestPaper(TestPaperParam param, String userId, TestPaper cache, int finalScore) {
		// 形成Testpaper对象保存到DB里
		TestPaper testPaper = new TestPaper();
		testPaper.setCode(cache.getCode());
		testPaper.setCategoryName(cache.getCategoryName());
		
		testPaper.setScore(finalScore);
		testPaper.setPassScore(cache.getPassScore());
		testPaper.setTotalScore(cache.getTotalScore());
		
		testPaper.setTotalTime(cache.getTotalTime());
		testPaper.setSpendTime(param.getSpendSeconds());
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat();
		testPaper.setTestDatetime(df.format(now));
		if (finalScore >= cache.getPassScore()) {
			testPaper.setPassFlag(1);
		} else {
			testPaper.setPassFlag(0);
		}
		testPaper.setItemCount(cache.getItemCount());
		testPaper.setUserId(userId);
		
		return testPaper;
	}

}
