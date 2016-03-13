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
	@Test
	public void CartMaxTest() {
		for (int i = 0; i < 52; i++) {
			cart.AddItem("potato");
			assertTrue(cart.GetSize() <= 50);
		}
	}
	
	@Test
	public void TestCartSize() {
		System.out.println(cart.GetSize());
		cart.EmptyCart();
		assertEquals(cart.GetSize(), 0);
		cart.AddItem("banana", 30);
		assertEquals(cart.GetSize(), 30);
	}
}
