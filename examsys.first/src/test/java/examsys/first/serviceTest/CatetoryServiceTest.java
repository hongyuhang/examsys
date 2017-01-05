package examsys.first.serviceTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import examsys.first.domain.Category;
import examsys.first.service.CategoryService;

/**
 * 考试类型单体测试类
 * @author hongyuhang
 *
 */

//@RunWith(SpringJUnit4ClassRunner.class) 
//@WebAppConfiguration(value = "src/main/webapp")
//@ContextConfiguration(locations={"classpath:applicationContext-main.xml","classpath:applicationContext-mybatis.xml"})

public class CatetoryServiceTest extends AbstractBaseServiceTest {
	@Autowired
	private CategoryService service;
//	private static Logger logger = Logger.getLogger(CatetoryTest.class);
	
	@Test
	public void testGetCategory() {
		Category category= service.getCategory("01");
		assertNotNull(category.getComposes());
		assertEquals(category.getComposes().size(), 3);
		assertEquals(category.getName(), "交通法规");
	}
	
	@Test
	public void testGetAllCatgory() {
		List<Category> category= service.getAllCatgory();
		assertNotNull(category);
		assertEquals(category.size(), 2);
	}
}
