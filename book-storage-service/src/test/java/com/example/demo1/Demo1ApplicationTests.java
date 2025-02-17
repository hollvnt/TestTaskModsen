package com.example.demo1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Demo1ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void concatTest(){
		String strOne = "one";
		String strTwo = "two";

		assertEquals("one two", strOne + strTwo);
	}

}
