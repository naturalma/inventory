package com.contineo.codetest.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryConfiguration implements Comparable<CategoryConfiguration>{
	private String categoryName;
	private List<String> validSubCategoryList;

	public CategoryConfiguration(String categoryName) {
		super();
		this.categoryName = categoryName;
		this.validSubCategoryList = new ArrayList<>();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getValidSubCategoryList() {
		return validSubCategoryList;
	}

	public void addValidSubCategory(String subCategoryName) {
		validSubCategoryList.add(subCategoryName);
	}

	public void removeValidSubCategory(String subCategoryName) {
		validSubCategoryList.remove(subCategoryName);
	}

	@Override
	public int compareTo(CategoryConfiguration o) {
		return this.getCategoryName().compareTo(o.categoryName);
	}
}
