package com.inventory.tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.inventory.store.Customer;

public class CustomerTests {
	@Test
	public void TestMembership() {
		Customer c1 = new Customer(false, false);
		Customer c2 = new Customer (true, false);
		assertFalse(c1.GetMemberStatus());
		assertTrue(c2.GetMemberStatus());
	}
	
	@Test
	public void TestTaxExemption() {
		Customer c1 = new Customer(false, false);
		Customer c2 = new Customer (false, true);
		assertFalse(c1.GetTaxStatus());
		assertTrue(c2.GetTaxStatus());
	}
}
