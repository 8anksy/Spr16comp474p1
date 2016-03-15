package com.inventory.tests;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.inventory.MockDB.ItemDO;
import com.inventory.store.Customer;
import com.inventory.store.Register;
import com.inventory.store.ShoppingCart;

public class RegisterTests {
	ShoppingCart cart = new ShoppingCart();
	ItemDO db = new ItemDO();
	Register r = new Register(db);
	Customer noMemNoEx = new Customer(false, false);
	Customer noMemEx = new Customer(false, true);
	Customer memNoEx = new Customer(true, false);
	Customer memEx = new Customer(true);
	ShoppingCart cart4 = new ShoppingCart();
	
	@Before
	public void ResetTests() {
		r = new Register(db);
		cart.emptyCart();
		cart.addItem("banana"); 	// 0.19
		cart.addItem("avocado");	// 0.99
		cart.addItem("sugar"); 		// 2.89
		cart.addItem("chardonnay");	// 8.99  = 13.06
	}
	@Test
	public void TestCalcRawTotal() {
		//Add a a bunch of stuff to a cart. See if it adds right.
		r.calcPurchasePrice(cart, noMemNoEx);
		assertEquals(13.06f, r.geBDRawTotal().floatValue(), 0);

	}
	@Test
	public void TestWrongItem() {
		cart.addItem("trampoline");
		r.calcPurchasePrice(cart, noMemNoEx);
		assertEquals(13.06f, r.geBDRawTotal().floatValue(), 0);
	}
	@Test
	public void TestEmptyCart() {
		cart.emptyCart();
		r.calcPurchasePrice(cart, noMemNoEx);
		assertEquals(0, r.geBDRawTotal().floatValue(), 0);
	}
	
	@Test
	public void TestCalcNonMemberDiscount() {
		ShoppingCart discCart = new ShoppingCart();
		
		//Test with 9 items
		discCart.addItem("avocado", 9);
		r.calcPurchasePrice(discCart, noMemNoEx);
		assertEquals(8.46f,r.getBDNetTotal().floatValue(), 0);
		
		
		//Test with 10 items
		discCart.addItem("avocado");
		r.calcPurchasePrice(discCart, noMemNoEx);
		assertEquals(8.91f,r.getBDNetTotal().floatValue(), 0);
		
		
		//Test with 11 items
		discCart.addItem("avocado");
		r.calcPurchasePrice(discCart, noMemNoEx);
		assertEquals(9.80f,r.getBDNetTotal().floatValue(), 0);
	}
	@Test
	public void TestCalcMemberDiscount() {
		ShoppingCart discCart = new ShoppingCart();
		
		//Test with 4 items
		discCart.addItem("avocado", 4);
		r.calcPurchasePrice(discCart, memNoEx);
		assertEquals(3.56f,r.getBDNetTotal().floatValue(), 0);
		
		//Test with 5 items
		discCart.addItem("avocado");
		r.calcPurchasePrice(discCart, memNoEx);
		assertEquals(4.45f,r.getBDNetTotal().floatValue(), 0);
		
		//Test with 6 items
		discCart.addItem("avocado"); //5.94
		r.calcPurchasePrice(discCart, memNoEx);
		assertEquals(5.08f,r.getBDNetTotal().floatValue(), 0);	
	}
	
	@Test
	public void TestTaxTotal () {
		ShoppingCart taxCart = new ShoppingCart();
		
		taxCart.addItem("milk", 5); //11.45
		
		//testing tax exempt
		r.calcPurchasePrice(taxCart, noMemEx);
		assertEquals(11.97f, r.getBDAftTaxTotal().floatValue(), 0);
		
		//testing non-tax-exempt
		r.calcPurchasePrice(taxCart, noMemNoEx);
		assertEquals(11.97f, r.getBDAftTaxTotal().floatValue(), 0);
		
	}
	
	@Test
	public void TestPrint() {
		cart.addItem("banana", 6);
		r.calcPurchasePrice(cart, noMemNoEx);
		r.printItems(cart);
		r.printDisplay();
	}
	
}