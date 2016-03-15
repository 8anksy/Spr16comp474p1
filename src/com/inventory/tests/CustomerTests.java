package com.inventory.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import com.inventory.store.Customer;

public class CustomerTests {
	@Test
	public void TestMembership() {
		Customer c1 = new Customer(false, false);
		Customer c2 = new Customer (true, false);
		assertFalse(c1.getMemberStatus());
		assertTrue(c2.getMemberStatus());
	}
	
	@Test
	public void TestTaxExemption() {
		Customer c1 = new Customer(false, false);
		Customer c2 = new Customer (false, true);
		assertFalse(c1.getTaxStatus());
		assertTrue(c2.getTaxStatus());
	}
}
