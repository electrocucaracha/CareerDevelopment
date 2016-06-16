package com.electrocucaracha.apps.cdp.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.electrocucaracha.apps.cdp.dao.CategoryDAO;

public class CategoryTest {

	private CategoryDAO controller;

	@Before
	public void setUp() throws Exception {
		controller = new CategoryDAO();
	}

	@Test
	public void createOneCategory() {
		List<Category> result = controller.retrieve();
		int count = result.size();
		assertTrue(count >= 0);

		Category category = new Category("test", "Definition", 100.0, 50.0);

		controller.create(category);
		assertNotNull(category.getId());

		Category saved = controller.get(category.getId());
		assertEquals(category, saved);
	}

	@Test
	public void retrieveOneCategory() {
		final int count = 10;
		for (int i = 0; i <= count; i++) {
			controller.create(new Category("test" + i, "Definition", 100.0, 50.0));
		}

		List<Category> result = controller.retrieve();
		assertTrue(result.size() >= count);
	}

	@Test
	public void deleteExistingCategory() {
		List<Category> result = controller.retrieve();
		int count = result.size();
		assertTrue(count >= 0);

		Category category = new Category("test", "Definition", 100.0, 50.0);

		controller.create(category);
		result = controller.retrieve();
		assertEquals(count + 1, result.size());

		controller.delete(category);
		result = controller.retrieve();
		assertEquals(count, result.size());
	}

	@Test
	public void updateOneCategory() {
		Category category = new Category("test", "Definition", 100.0, 50.0);
		controller.create(category);
		assertNotNull(category.getId());

		String newCategory = "test2";
		String newDefinition = "Definition2";
		double newBudget = 50.0;
		double newTime = 25.0;
		category.setCategory(newCategory);
		category.setDefinition(newDefinition);
		category.setBudget(newBudget);
		category.setTime(newTime);
		controller.update(category);

		Category updatedCategory = controller.get(category.getId());
		assertEquals(updatedCategory.getCategory(), newCategory);
		assertEquals(updatedCategory.getDefinition(), newDefinition);
		assertTrue(updatedCategory.getBudget() == newBudget);
		assertTrue(updatedCategory.getTime() == newTime);
	}

}
