package examsys.first.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import examsys.first.domain.Item;
import examsys.first.service.ItemService;

public class ItemServiceTest extends AbstractBaseServiceTest {
	@Autowired
	private ItemService service;
//	private static Logger logger = Logger.getLogger(CatetoryTest.class);
	
	@Test
	public void testGetRandomItemByType1() {
		List<Item> items= service.getRandomItems(1, 5);
		assertNotNull(items);
		assertEquals(items.size(), 5);
	}
	
	@Test
	public void testGetRandomItemByType2() {
		List<Item> items= service.getRandomItems(2, 2);
		assertNotNull(items);
		assertEquals(items.size(), 2);
	}
	
	@Test
	public void testGetRandomItemByType3() {
		List<Item> items= service.getRandomItems(3, 5);
		assertNotNull(items);
		assertEquals(items.size(), 5);
	}
}
