package com.contineo.codetest.inventory.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.contineo.codetest.inventory.model.CategoryConfiguration;

@Component
public class CategoryManager {
	private Map<String, CategoryConfiguration> categoryConfigurations = new HashMap<>();
	
	public CategoryManager() {
		reloadInitialData();
	}
	
	public void reloadInitialData() {
		categoryConfigurations.clear();
		
		CategoryConfiguration cat1 = new CategoryConfiguration("Food");
		cat1.addValidSubCategory("Fruits");
		cat1.addValidSubCategory("Vegetables");
		cat1.addValidSubCategory("Meats");
		
		CategoryConfiguration cat2 = new CategoryConfiguration("Apparel");
		cat2.addValidSubCategory("Shoes");
		cat2.addValidSubCategory("T-shirts");

		CategoryConfiguration cat3 = new CategoryConfiguration("Electronics");
		cat3.addValidSubCategory("Smart Phones");
		cat3.addValidSubCategory("Computers");
		cat3.addValidSubCategory("Cameras");
		
		categoryConfigurations.put(cat1.getCategoryName(), cat1);
		categoryConfigurations.put(cat2.getCategoryName(), cat2);
		categoryConfigurations.put(cat3.getCategoryName(), cat3);
	}
	
	public void addCategory(String name) {
		categoryConfigurations.put(name, new CategoryConfiguration(name));
	}

	public void deleteCategory(String category) {
		categoryConfigurations.remove(category);
	}

	public void addValidSubCategory(String categoryName, String subCategoryName) {
		categoryConfigurations.get(categoryName).addValidSubCategory(subCategoryName);
	}

	public void deleteValidSubCategory(String categoryName, String subCategoryName) {
		categoryConfigurations.get(categoryName).removeValidSubCategory(subCategoryName);
	}

	public List<String> loadCategoryNames() {
		List<String> catNameList = new ArrayList<>();
		catNameList.addAll(categoryConfigurations.keySet());
		Collections.sort(catNameList);
		return catNameList;
	}

	public List<CategoryConfiguration> loadCategoryConfigurations() {
		List<CategoryConfiguration> catList = new ArrayList<>();
		catList.addAll(categoryConfigurations.values());
		Collections.sort(catList);
		return catList;
	}

	public List<String> loadSubCategoryNames(String categoryName) {
		List<String> subCatNameList = new ArrayList<>();
		subCatNameList.addAll(categoryConfigurations.get(categoryName).getValidSubCategoryList());
		Collections.sort(subCatNameList);
		return subCatNameList;
	}

}
