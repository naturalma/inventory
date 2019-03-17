package com.contineo.codetest.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contineo.codetest.inventory.manager.CategoryManager;
import com.contineo.codetest.inventory.manager.InventoryManager;
import com.contineo.codetest.inventory.model.Inventory;

@Controller
public class InventoryController {
	@Autowired
	private CategoryManager categoryManager;

	@Autowired
	private InventoryManager inventoryManager;
	
	@GetMapping("/")
	public String browse(
			@RequestParam(name = "inv_cat", required = false, defaultValue = "") String category, 
			@RequestParam(name = "inv_subcat", required = false, defaultValue = "") String subCategory, 
			@RequestParam(name = "inv_name", required = false, defaultValue = "") String name, 
			@RequestParam(name = "sort", required = false, defaultValue = "name") String sort, 
			Model model) {
		model.addAttribute("invList", inventoryManager.searchInventory(category, subCategory, name, sort));
		model.addAttribute("catNameList", categoryManager.loadCategoryNames());
		return "browse";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("catList", categoryManager.loadCategoryConfigurations());
		return "admin";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id", required = true) String id, Model model) {
		inventoryManager.deleteInventory(Integer.valueOf(id));
		return "redirect:/";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam(name = "id", required = true) String id, Model model) {
		model.addAttribute("inv", inventoryManager.getInventoryById(Integer.valueOf(id)));
		model.addAttribute("catNameList", categoryManager.loadCategoryNames());
		model.addAttribute("type", "Update");
		return "modify";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("inv", new Inventory(0, "", "", "", 0));
		model.addAttribute("catNameList", categoryManager.loadCategoryNames());
		model.addAttribute("type", "Add");
		return "modify";
	}
	
	@PostMapping("/commitChange")
	public String commitChange(
			@RequestParam(name = "action_type", required = true) String type, 
			@RequestParam(name = "inv_id", required = true) String id, 
			@RequestParam(name = "inv_cat", required = true) String category, 
			@RequestParam(name = "inv_subcat", required = true) String subCategory, 
			@RequestParam(name = "inv_name", required = true) String name, 
			@RequestParam(name = "inv_quantity", required = true) String quantity, 
			Model model) {
		if (type.equals("Add")) {
			inventoryManager.addInventory(new Inventory(Integer.parseInt(id), category, subCategory, name, Integer.parseInt(quantity)));
		} else if (type.equals("Update")) {
			inventoryManager.updateInventory(new Inventory(Integer.parseInt(id), category, subCategory, name, Integer.parseInt(quantity)));
		}
		return "redirect:/";
	}
	
	@GetMapping("/addCat")
	public String addCat(
			@RequestParam(name = "cat", required = true) String category, 
			Model model) {
		categoryManager.addCategory(category);
		return "redirect:/admin";
	}
	
	@GetMapping("/delCat")
	public String delCat(
			@RequestParam(name = "cat", required = true) String category, 
			Model model) {
		categoryManager.deleteCategory(category);
		return "redirect:/admin";
	}
	
	@GetMapping("/addSubCat")
	public String addSubCat(
			@RequestParam(name = "cat", required = true) String category, 
			@RequestParam(name = "subcat", required = true) String subCategory, 
			Model model) {
		categoryManager.addValidSubCategory(category, subCategory);
		return "redirect:/admin";
	}
	
	@GetMapping("/delSubCat")
	public String delSubCat(
			@RequestParam(name = "cat", required = true) String category, 
			@RequestParam(name = "subcat", required = true) String subCategory, 
			Model model) {
		categoryManager.deleteValidSubCategory(category, subCategory);
		return "redirect:/admin";
	}
	
}
