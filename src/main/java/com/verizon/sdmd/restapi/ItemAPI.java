package com.verizon.sdmd.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.sdmd.model.Item;
import com.verizon.sdmd.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemAPI {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public ResponseEntity<List<Item>> getAllItems() {
		ResponseEntity<List<Item>> resp = null;
		List<Item> items = itemService.getItems();
		if (items != null && items.size() > 0)
			resp = new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		else
			resp = new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);

		return resp;
	}

	@GetMapping("/{iCode}")
	public ResponseEntity<Item> getItem(@PathVariable("iCode") int icode) {
		ResponseEntity<Item> resp = null;
		Item item = itemService.getItem(icode);
		if (item != null)
			resp = new ResponseEntity<Item>(item, HttpStatus.OK);
		else
			resp = new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		return resp;
	}

	@PostMapping
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		ResponseEntity<Item> resp = null;

		if (item != null && !itemService.exists(item.getiCode()))
		{
			itemService.addItem(item);
			resp = new ResponseEntity<Item>(item, HttpStatus.OK);
		} 
		else
			resp = new ResponseEntity<Item>(HttpStatus.NOT_FOUND);

		return resp;
	}

	@PutMapping
	public ResponseEntity<Item> updateItem(@RequestBody Item item) {
		ResponseEntity<Item> resp = null;
		if (item != null && itemService.exists(item.getiCode())) {
			itemService.updateItem(item);
			resp = new ResponseEntity<Item>(item, HttpStatus.OK);
		} else
			resp = new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		return resp;
	}

	@DeleteMapping("/{icode}")
	public ResponseEntity<Void> delItem(@PathVariable("icode") int icode) {
		ResponseEntity<Void> resp = null;
		if (itemService.exists(icode)) {
			itemService.removeItem(icode);
			resp = new ResponseEntity<>(HttpStatus.OK);
		} else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return resp;
	}

}
