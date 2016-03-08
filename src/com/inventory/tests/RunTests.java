package com.inventory.tests;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class RunTests {
	private Result result;
	
	public RunTests() {
		 this.result = JUnitCore.runClasses(RegisterTests.class);
	}
	
	public void Run() {
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}
