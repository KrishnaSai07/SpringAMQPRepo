package com.verizon.sdmd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.sdmd.dao.ItemMongoRepository;
import com.verizon.sdmd.model.Item;

@Service
public class ItemSreviceImpl implements ItemService {

	@Autowired
	private ItemMongoRepository itemDao;
	
	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.insert(item);

	}

	@Override
	public void removeItem(int iCode) {
		// TODO Auto-generated method stub
        itemDao.deleteById(iCode);
	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
         itemDao.save(item);
	}

	@Override
	public Item getItem(int iCode) {
		// TODO Auto-generated method stub
		Optional<Item> opt= itemDao.findById(iCode);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public List<Item> getItems() {
		// TODO Auto-generated method stub
		return itemDao.findAll();
	}

	@Override
	public boolean exists(int iCode) {
		// TODO Auto-generated method stub
		return itemDao.existsById(iCode);
	}

}
