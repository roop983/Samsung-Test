package com.samsung.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {
	
	@Test
	public void add() {
		System.out.println("ADD");
		int a =10;
		int b =20;
		Assert.assertEquals(30, a+b);
		
	}
	
	@Test
	public void sub() {
		System.out.println("SUB");
		int a =40;
		int b =20;
		Assert.assertEquals(20, a-b);
		
	}


}
