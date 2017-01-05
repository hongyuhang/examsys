package examsys.first.service;

import java.util.List;

import examsys.first.domain.Item;

public interface ItemService {
	/**
	 * 根据类型获得随机数量的考题
	 * @param type
	 * @param count
	 * @return
	 */
	public List<Item> getRandomItems(Integer type, Integer count);
}
