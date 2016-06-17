package com.electrocucaracha.apps.cdp.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.electrocucaracha.apps.cdp.dao.TaskDAO;

public class TaskTest {

	private TaskDAO controller;

	@Before
	public void setUp() throws Exception {
		controller = new TaskDAO();
	}

	@Test
	public void createOneTask() {
		List<Task> result = controller.retrieve();
		int count = result.size();
		assertTrue(count >= 0);

		Task task = new Task("test", "resource", 50.0, 15.0, "comments");

		controller.create(task);
		assertNotNull(task.getId());

		Task saved = controller.get(task.getId());
		assertEquals(task, saved);
	}

	@Test
	public void retrieveOneTask() {
		final int count = 10;
		for (int i = 0; i <= count; i++) {
			controller.create(new Task("test" + i, "resource", 50.0, 15.0, "comments"));
		}

		List<Task> result = controller.retrieve();
		assertTrue(result.size() >= count);
	}

	@Test
	public void deleteExistingTask() {
		List<Task> result = controller.retrieve();
		int count = result.size();
		assertTrue(count >= 0);

		Task task = new Task("test", "resource", 50.0, 15.0, "comments");

		controller.create(task);
		result = controller.retrieve();
		assertEquals(count + 1, result.size());

		controller.delete(task);
		result = controller.retrieve();
		assertEquals(count, result.size());
	}

	@Test
	public void updateOneTask() {
		Task task = new Task("test", "resource", 50.0, 15.0, "comments");
		controller.create(task);
		assertNotNull(task.getId());

		String newTask = "test2";
		String newResource = "resource2";
		Double newProgress = 100.0;
		Double newPrice = 30.0;
		String newComments = "comments2";
		task.setTask(newTask);
		task.setResource(newResource);
		task.setProgress(newProgress);
		task.setPrice(newPrice);
		task.setComments(newComments);
		
		controller.update(task);

		Task updatedTask = controller.get(task.getId());
		assertEquals(updatedTask.getTask(), newTask); 
		assertEquals(updatedTask.getResource(), newResource);
		assertTrue(updatedTask.getProgress() == newProgress);
		assertTrue(updatedTask.getPrice() == newPrice);
		assertEquals(updatedTask.getComments(), newComments);
		
	}

}
