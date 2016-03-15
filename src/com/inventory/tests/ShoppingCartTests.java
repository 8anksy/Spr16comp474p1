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
		cart.EmptyCart();
	}
	
	/*
	 * This tests the requirement that our cart is not to exceed 50 items.
	 * It attempts to add 52 items and checks the cart size at the end.
	 */
	@Test
	public void CartMaxTest() {
		for (int i = 0; i < 52; i++) {
			cart.AddItem("potato");
			assertTrue(cart.GetSize() <= 50);
		}
	}
	
	/*
	 * This tests that our methods for adding items and emptying the cart is functional.
	 */
	@Test
	public void TestCartSize() {
		assertEquals(0, cart.GetSize());
		cart.AddItem("banana", 30);
		assertEquals(30, cart.GetSize());
		
		cart.RemoveQuantity("banana", 5);
		assertEquals(25, cart.GetSize());
	}
}
