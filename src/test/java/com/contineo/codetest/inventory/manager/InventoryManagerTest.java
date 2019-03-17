package com.contineo.codetest.inventory.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.contineo.codetest.inventory.model.Inventory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CategoryManagerTest.class})
@Configuration
@ComponentScan(basePackages = {"com.contineo.codetest.inventory.manager"})
public class InventoryManagerTest {

	@Autowired
	private InventoryManager inventoryManager;
	
	@Before
	public void setUp() {
		inventoryManager.reloadInitialData();
	}
	
	@Test
	public void testAddInventory() {
		inventoryManager.addInventory(new Inventory(0, "Apparel", "Shoes", "New Balance", 5));
		Inventory inventory = inventoryManager.getInventoryById(4);
		
		assertNotNull(inventory);
		assertEquals("Apparel", inventory.getCategory());
		assertEquals("Shoes", inventory.getSubCategory());
		assertEquals("New Balance", inventory.getName());
		assertEquals(5, inventory.getQuantity());
	}

	@Test
	public void testDeleteInventory() {
		inventoryManager.deleteInventory(2);
		assertNull(inventoryManager.getInventoryById(2));
	}

	@Test
	public void testUpdateInventory() {
		inventoryManager.updateInventory(new Inventory(3, "Apparel", "Shoes", "New Balance", 2));
		Inventory inventory = inventoryManager.getInventoryById(3);
		
		assertNotNull(inventory);
		assertEquals("Apparel", inventory.getCategory());
		assertEquals("Shoes", inventory.getSubCategory());
		assertEquals("New Balance", inventory.getName());
		assertEquals(2, inventory.getQuantity());
	}

	@Test
	public void testSearchInventoryByCategory() {
		List<Inventory> invList = inventoryManager.searchInventory("Apparel", "", "", "name");

		assertEquals(2, invList.size());
		
		assertEquals("Apparel", invList.get(0).getCategory());
		assertEquals("Shoes", invList.get(0).getSubCategory());
		assertEquals("Addidas", invList.get(0).getName());
		assertEquals(5, invList.get(0).getQuantity());

		assertEquals("Apparel", invList.get(1).getCategory());
		assertEquals("Shoes", invList.get(1).getSubCategory());
		assertEquals("Nike", invList.get(1).getName());
		assertEquals(5, invList.get(1).getQuantity());
	}

	@Test
	public void testSearchInventoryBySubCategory() {
		List<Inventory> invList = inventoryManager.searchInventory("Food", "Fruits", "", "name");

		assertEquals(1, invList.size());
		
		assertEquals("Food", invList.get(0).getCategory());
		assertEquals("Fruits", invList.get(0).getSubCategory());
		assertEquals("Apple", invList.get(0).getName());
		assertEquals(3, invList.get(0).getQuantity());
	}

	@Test
	public void testSearchInventoryByName() {
		List<Inventory> invList = inventoryManager.searchInventory("", "", "Addidas", "name");

		assertEquals(1, invList.size());
		
		assertEquals("Apparel", invList.get(0).getCategory());
		assertEquals("Shoes", invList.get(0).getSubCategory());
		assertEquals("Addidas", invList.get(0).getName());
		assertEquals(5, invList.get(0).getQuantity());
	}

	@Test
	public void testSearchInventorySortByCategory() {
		List<Inventory> invList = inventoryManager.searchInventory("", "", "", "cat");

		assertEquals(3, invList.size());
		
		assertEquals("Apparel", invList.get(0).getCategory());
		assertEquals("Shoes", invList.get(0).getSubCategory());
		assertEquals("Nike", invList.get(0).getName());
		assertEquals(5, invList.get(0).getQuantity());

		assertEquals("Apparel", invList.get(1).getCategory());
		assertEquals("Shoes", invList.get(1).getSubCategory());
		assertEquals("Addidas", invList.get(1).getName());
		assertEquals(5, invList.get(1).getQuantity());

		assertEquals("Food", invList.get(2).getCategory());
		assertEquals("Fruits", invList.get(2).getSubCategory());
		assertEquals("Apple", invList.get(2).getName());
		assertEquals(3, invList.get(2).getQuantity());
	}

	@Test
	public void getInventoryById() {
		Inventory inventory = inventoryManager.getInventoryById(1);
		
		assertNotNull(inventory);
		assertEquals("Food", inventory.getCategory());
		assertEquals("Fruits", inventory.getSubCategory());
		assertEquals("Apple", inventory.getName());
		assertEquals(3, inventory.getQuantity());
	}
}
