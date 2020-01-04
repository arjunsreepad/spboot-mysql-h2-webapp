package com.example.TodoDemo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.TodoDemo.TodoItem;

/*
 * This is simple test case just to showcase integration with jacoco/cobertura this test will not serve any real time purpose
 */
public class TodoItemTest {

	@Test
	public void sampleTest() {
		TodoItem todoItem = new TodoItem();
		todoItem.setCategory("Test Category");
		String valueFromToDOItem = todoItem.getCategory();
		assertEquals("Test Category", valueFromToDOItem);
	}

}
