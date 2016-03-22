package com.imdganesan.ifelseladder;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IfTest {
	
	boolean isTestApi1Called;
	boolean isTestApi2Called;
	boolean isTestApi3Called;
	
	public String testApi1() {
		isTestApi1Called = true;
		return "Test1";
	}

	public String testApi2() {
		isTestApi2Called = true;
		return "Test2";
	}
	
	public String testApi3() {
		isTestApi3Called = true;
		return "Test3";
	}
	
	@Before
	public void setup() {
		isTestApi1Called = false;
		isTestApi2Called = false;
		isTestApi3Called = false;
	}

	@Test
	public void isWorksSimpleIfThen() {		
		assertEquals("True executes then result", "Test1", If.of(String.class).when(true).then("Test1").otherwise("Test2"));
		assertEquals("false executes else result", "Test2", If.of(String.class).when(false).then("Test1").otherwise("Test2"));
	}
	
	@Test
	public void isWorksWithBooleanResultLamda() {
		assertEquals("True executes then result", "Test1", If.of(String.class).when(() -> true).then(() -> "Test1").otherwise(() -> "Test2"));
		assertEquals("false executes else result", "Test2", If.of(String.class).when(() -> false).then(() -> "Test1").otherwise(() -> "Test2"));
	}
	
	@Test
	public void isWorksWithMultipleResultLamda() {
		assertEquals("True executes then1 result", "Test1", If.of(String.class).when(() -> true).then(() -> "Test1").otherwiseWhen(()-> true).then(() -> "Test2") .otherwise(() -> "Test3"));
		assertEquals("True executes then2 result", "Test2", If.of(String.class).when(() -> false).then(() -> "Test1").otherwiseWhen(()-> true).then(() -> "Test2") .otherwise(() -> "Test3"));
		assertEquals("True executes then3 result", "Test3", If.of(String.class).when(() -> false).then(() -> "Test1").otherwiseWhen(()-> false).then(() -> "Test2") .otherwise(() -> "Test3"));
	}
	
	@Test
	public void isWorksWithMultipleResultLamdaExecutionTest() {
		assertEquals("True executes then1 result", "Test1", If.of(String.class).when(() -> true).then(() -> testApi1()).otherwiseWhen(()-> true).then(() -> testApi2()) .otherwise(() -> testApi3()));
		assertTrue("Api 1 not executed", isTestApi1Called);
		assertFalse("Api 2 is executed", isTestApi2Called);
		assertFalse("Api 3 is executed", isTestApi3Called);
	}
	
	@Test
	public void isWorksWithMultipleResultLamdaExecutionTest2() {
		assertEquals("True executes then2 result", "Test2", If.of(String.class).when(() -> false).then(() -> testApi1()).otherwiseWhen(()-> true).then(() -> testApi2()) .otherwise(() -> testApi3()));
		assertFalse("Api 1 is executed", isTestApi1Called);
		assertTrue("Api 2 not executed", isTestApi2Called);
		assertFalse("Api 3 is executed", isTestApi3Called);
	}
	
	@Test
	public void isWorksWithMultipleResultLamdaExecutionTest3() {
		assertEquals("True executes then3 result", "Test3", If.of(String.class).when(() -> false).then(() -> testApi1()).otherwiseWhen(()-> false).then(() -> testApi2()) .otherwise(() -> testApi3()));
		assertFalse("Api 1 is executed", isTestApi1Called);
		assertFalse("Api 2 is executed", isTestApi2Called);
		assertTrue("Api 3 not executed", isTestApi3Called);
	}
	
	@Test
	public void isWorksWithaAsExecuble() {
		Executable<String> executable = If.of(String.class).when(() -> true).then(() -> testApi1()).otherwiseWhen(()-> true).then(() -> testApi2()).asExecutable(() -> testApi3());
		assertFalse("Api 1 is executed", isTestApi1Called);
		assertFalse("Api 2 is executed", isTestApi2Called);
		assertFalse("Api 3 is executed", isTestApi3Called);
		assertEquals("True executes then1 result", "Test1", executable.execute());
		assertTrue("Api 1 not executed", isTestApi1Called);
		assertFalse("Api 2 is executed", isTestApi2Called);
		assertFalse("Api 3 is executed", isTestApi3Called);
	}
	
}
