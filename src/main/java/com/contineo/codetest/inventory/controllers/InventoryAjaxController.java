package com.contineo.codetest.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contineo.codetest.inventory.manager.CategoryManager;
import com.contineo.codetest.inventory.manager.InventoryManager;

@RestController
public class InventoryAjaxController {
	@Autowired
	private CategoryManager categoryManager;

	@Autowired
	private InventoryManager inventoryManager;
	
	@GetMapping("/getSubCategoryList")
	public List<String> getSubCategoryList(
			@RequestParam(name = "inv_cat", required = true) String categoryName, 
			Model model) {
		return categoryManager.loadSubCategoryNames(categoryName);
	}
	

}
