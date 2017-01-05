package examsys.first.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examsys.first.dao.ItemMapper;
import examsys.first.domain.Item;
import examsys.first.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper mapper; 
	@Override
	public List<Item> getRandomItems(Integer type, Integer count) {
		List<Item> items = mapper.getRandomItems(type, count);
		return items;
	}

}
