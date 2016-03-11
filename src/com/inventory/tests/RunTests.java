package com.inventory.tests;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class RunTests {
	private Result customerResult;
	private Result cartResult;
	private Result registerResult;
	
	public RunTests() {
		 this.customerResult = JUnitCore.runClasses(CustomerTests.class);
		 this.cartResult = JUnitCore.runClasses(ShoppingCartTests.class);
		 this.registerResult = JUnitCore.runClasses(RegisterTests.class);
	}
	
	public void Run() {
		for (Failure failure : customerResult.getFailures()) {
			System.out.println(failure.toString());
		}
		for (Failure failure : cartResult.getFailures()) {
			System.out.println(failure.toString());
		}
		for (Failure failure : registerResult.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println(customerResult.wasSuccessful());
		System.out.println(cartResult.wasSuccessful());
		System.out.println(registerResult.wasSuccessful());
	}
}
