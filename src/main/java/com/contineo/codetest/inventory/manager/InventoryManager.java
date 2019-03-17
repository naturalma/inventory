package com.contineo.codetest.inventory.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.contineo.codetest.inventory.model.Inventory;

@Component
public class InventoryManager {
	// memory based inventory
	Map<Integer, Inventory> inventoryStore = new HashMap<>();

	public InventoryManager () {
		reloadInitialData();
	}
	
	// initial data
	public void reloadInitialData() {
		inventoryStore.clear();
		
		inventoryStore.put(1, new Inventory(1, "Food", "Fruits", "Apple", 3));
		inventoryStore.put(2, new Inventory(2, "Apparel", "Shoes", "Nike", 5));
		inventoryStore.put(3, new Inventory(3, "Apparel", "Shoes", "Addidas", 5));
	}
	
	public void addInventory(Inventory inv) {
		inventoryStore.keySet().forEach(id -> inv.setId(Math.max(id, inv.getId())));
		inv.setId(inv.getId() + 1);
		inventoryStore.put(inv.getId(), inv);
	}

	public void deleteInventory(int id) {
		inventoryStore.remove(id);
	}

	public void updateInventory(Inventory inv) {
		inventoryStore.put(inv.getId(), inv);
	}

	public List<Inventory> searchInventory(String category, String subCategory, String name, String sort) {
		List<Inventory> invList = new ArrayList<>();
		inventoryStore.forEach((id, inv) -> {
			boolean filter = true;
			if (!StringUtils.isEmpty(category) && !category.equals(inv.getCategory())) {
				filter = false;
			}
			if (!StringUtils.isEmpty(subCategory) && !subCategory.equals(inv.getSubCategory())) {
				filter = false;
			}
			if (!StringUtils.isEmpty(name) && !inv.getName().contains(name)) {
				filter = false;
			}
			if (filter) {
				invList.add(inv);
			}
		});
		
		if ("name".equals(sort)) {
			Collections.sort(invList, (inv1, inv2) -> inv1.getName().compareTo(inv2.getName()));
		} else if ("cat".equals(sort)) {
			Collections.sort(invList, (inv1, inv2) -> 
				inv1.getCategory().equals(inv2.getCategory())?
						inv1.getSubCategory().compareTo(inv2.getSubCategory()):inv1.getCategory().compareTo(inv2.getCategory()));
		}
		return invList;
	}

	public Inventory getInventoryById(int id) {
		return inventoryStore.get(id);
	}
}
