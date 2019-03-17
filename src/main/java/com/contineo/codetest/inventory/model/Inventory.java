package com.contineo.codetest.inventory.model;

public class Inventory {
	private int id;
	private String category;
	private String subCategory;
	private String name;
	private int quantity;
	
	public Inventory(int id, String category, String subCategory, String name, int quantity) {
		super();
		this.id = id;
		this.category = category;
		this.subCategory = subCategory;
		this.name = name;
		this.quantity = quantity;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
