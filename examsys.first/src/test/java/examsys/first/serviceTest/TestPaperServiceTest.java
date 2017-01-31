package examsys.first.serviceTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import examsys.first.domain.Category;
import examsys.first.domain.TestPaper;
import examsys.first.enums.ItemType;
import examsys.first.service.CategoryService;
import examsys.first.service.TestPaperService;

public class TestPaperServiceTest extends AbstractBaseServiceTest {

	@Autowired
	private TestPaperService service;
	@Autowired
	private CategoryService categoryService;
	
	private Category category;
	
	@Before
	public void prepareData() {
		category = categoryService.getCategory("01");
	}
	
	@Test
	public void testgenerateTestPaperParamNull() {
		TestPaper testPaper = service.generateTestPaper(null, null);
		assertNull(testPaper);
	}
	
	@Test
	public void testgenerateTestPaperNormal() {
		TestPaper testPaper = service.generateTestPaper(category, "testUserId");
		assertNotNull(testPaper);
		assertEquals(testPaper.getTestContent().size(), 3);
		
		assertNotNull(testPaper.getTestContent().get(ItemType.SINGLE.getValue()));
		assertEquals(testPaper.getTestContent().get(ItemType.SINGLE.getValue()).size(), 5);
		
		assertNotNull(testPaper.getTestContent().get(ItemType.JUDGE.getValue()));
		assertEquals(testPaper.getTestContent().get(ItemType.JUDGE.getValue()).size(), 5);
		
		assertNotNull(testPaper.getTestContent().get(ItemType.MUTI.getValue()));
		assertEquals(testPaper.getTestContent().get(ItemType.MUTI.getValue()).size(), 2);
	}
	
}
