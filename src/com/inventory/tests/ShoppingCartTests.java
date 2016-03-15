package com.inventory.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.inventory.MockDB.ItemDO;
import com.inventory.store.ShoppingCart;

public class ShoppingCartTests {
	ShoppingCart cart = new ShoppingCart();
	ItemDO db = new ItemDO();
	
	@Before
	public void ResetCart() {
		cart.emptyCart();
	}
	
	/*
	 * This tests the requirement that our cart is not to exceed 50 items.
	 * It attempts to add 52 items and checks the cart size at the end.
	 */
	@Test
	public void CartMaxTest() {
		for (int i = 0; i < 52; i++) {
			cart.addItem("potato");
			assertTrue(cart.getSize() <= 50);
		}
	}
	
	/*
	 * This tests that our methods for adding items and emptying the cart is functional.
	 */
	@Test
	public void TestCartSize() {
		assertEquals(0, cart.getSize());
		cart.addItem("banana", 30);
		assertEquals(30, cart.getSize());
		
		cart.removeQuantity("banana", 5);
		assertEquals(25, cart.getSize());
	}
}
