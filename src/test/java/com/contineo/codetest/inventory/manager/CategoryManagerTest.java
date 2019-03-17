package com.contineo.codetest.inventory.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.contineo.codetest.inventory.model.CategoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CategoryManagerTest.class})
@Configuration
@ComponentScan(basePackages = {"com.contineo.codetest.inventory.manager"})
public class CategoryManagerTest {
	@Autowired
	private CategoryManager categoryManager;
	
	@Before
	public void setUp() {
		categoryManager.reloadInitialData();
	}
	
	@Test
	public void testAddCategory() {
		assertFalse(categoryManager.loadCategoryNames().contains("Dummy Category"));
		categoryManager.addCategory("Dummy Category");
		assertTrue(categoryManager.loadCategoryNames().contains("Dummy Category"));
	}

	@Test
	public void testDeleteCategory() {
		assertTrue(categoryManager.loadCategoryNames().contains("Apparel"));
		categoryManager.deleteCategory("Apparel");
		assertFalse(categoryManager.loadCategoryNames().contains("Apparel"));
	}

	@Test
	public void testAddValidSubCategory() {
		assertFalse(categoryManager.loadSubCategoryNames("Apparel").contains("Dummy Sub-category"));
		categoryManager.addValidSubCategory("Apparel", "Dummy Sub-category");
		assertTrue(categoryManager.loadSubCategoryNames("Apparel").contains("Dummy Sub-category"));
	}

	@Test
	public void testDeleteValidSubCategory() {
		assertTrue(categoryManager.loadSubCategoryNames("Apparel").contains("Shoes"));
		categoryManager.deleteValidSubCategory("Apparel", "Shoes");
		assertFalse(categoryManager.loadSubCategoryNames("Apparel").contains("Shoes"));
	}

	@Test
	public void testLoadCategoryNames() {
		List<String> categoryNames = categoryManager.loadCategoryNames();
		assertEquals(3, categoryNames.size());
		assertEquals("Apparel", categoryNames.get(0));
		assertEquals("Electronics", categoryNames.get(1));
		assertEquals("Food", categoryNames.get(2));
	}

	@Test
	public void testLoadCategoryConfigurations() {
		List<CategoryConfiguration> categoryConfiguration = categoryManager.loadCategoryConfigurations();
		assertEquals(3, categoryConfiguration.size());

		assertEquals("Apparel", categoryConfiguration.get(0).getCategoryName());
		assertEquals(2, categoryConfiguration.get(0).getValidSubCategoryList().size());
		assertEquals("Shoes", categoryConfiguration.get(0).getValidSubCategoryList().get(0));
		assertEquals("T-shirts", categoryConfiguration.get(0).getValidSubCategoryList().get(1));

		assertEquals("Electronics", categoryConfiguration.get(1).getCategoryName());
		assertEquals(3, categoryConfiguration.get(1).getValidSubCategoryList().size());
		assertEquals("Smart Phones", categoryConfiguration.get(1).getValidSubCategoryList().get(0));
		assertEquals("Computers", categoryConfiguration.get(1).getValidSubCategoryList().get(1));
		assertEquals("Cameras", categoryConfiguration.get(1).getValidSubCategoryList().get(2));

		assertEquals("Food", categoryConfiguration.get(2).getCategoryName());
		assertEquals(3, categoryConfiguration.get(2).getValidSubCategoryList().size());
		assertEquals("Fruits", categoryConfiguration.get(2).getValidSubCategoryList().get(0));
		assertEquals("Vegetables", categoryConfiguration.get(2).getValidSubCategoryList().get(1));
		assertEquals("Meats", categoryConfiguration.get(2).getValidSubCategoryList().get(2));
	}

	@Test
	public void testLoadSubCategoryNames() {
		assertEquals(3, categoryManager.loadSubCategoryNames("Electronics").size());
		assertEquals("Cameras", categoryManager.loadSubCategoryNames("Electronics").get(0));
		assertEquals("Computers", categoryManager.loadSubCategoryNames("Electronics").get(1));
		assertEquals("Smart Phones", categoryManager.loadSubCategoryNames("Electronics").get(2));

	}

}
