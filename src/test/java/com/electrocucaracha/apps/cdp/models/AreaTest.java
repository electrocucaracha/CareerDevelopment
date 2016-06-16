package com.electrocucaracha.apps.cdp.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.electrocucaracha.apps.cdp.dao.AreaDAO;

public class AreaTest {

	private AreaDAO controller;

	@Before
	public void setUp() throws Exception {
		controller = new AreaDAO();
	}

	@Test
	public void createOneArea() {
		List<Area> result = controller.retrieve();
		int count = result.size();
		assertTrue(count >= 0);

		Area area = new Area("test");

		controller.create(area);
		assertNotNull(area.getId());

		Area saved = controller.get(area.getId());
		assertEquals(area, saved);
	}

	@Test
	public void retrieveOneArea() {
		final int count = 10;
		for (int i = 0; i <= count; i++) {
			controller.create(new Area("test" + i));
		}

		List<Area> result = controller.retrieve();
		assertTrue(result.size() >= count);
	}

	@Test
	public void deleteExistingArea() {
		List<Area> result = controller.retrieve();
		int count = result.size();
		assertTrue(count >= 0);

		Area area = new Area("test");

		controller.create(area);
		result = controller.retrieve();
		assertEquals(count + 1, result.size());

		controller.delete(area);
		result = controller.retrieve();
		assertEquals(count, result.size());
	}

	@Test
	public void updateOneArea() {
		Area area = new Area("test");
		controller.create(area);
		assertNotNull(area.getId());

		String newArea = "test2";
		area.setArea(newArea);
		controller.update(area);

		Area updatedArea = controller.get(area.getId());
		assertEquals(updatedArea.getArea(), newArea);
	}

}
