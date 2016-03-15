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
		cart.EmptyCart();
		cart.AddItem("banana"); 	// 0.19
		cart.AddItem("avocado");	// 0.99
		cart.AddItem("sugar"); 		// 2.89
		cart.AddItem("chardonnay");	// 8.99  = 13.06
	}
	@Test
	public void TestCalcRawTotal() {
		//Add a a bunch of stuff to a cart. See if it adds right.
		r.CalcPurchasePrice(cart, noMemNoEx);
		assertEquals(13.06f, r.BDGetRawTotal().floatValue(), 0);

	}
	@Test
	public void TestWrongItem() {
		cart.AddItem("trampoline");
		r.CalcPurchasePrice(cart, noMemNoEx);
		assertEquals(13.06f, r.BDGetRawTotal().floatValue(), 0);
	}
	@Test
	public void TestEmptyCart() {
		cart.EmptyCart();
		r.CalcPurchasePrice(cart, noMemNoEx);
		assertEquals(0, r.BDGetRawTotal().floatValue(), 0);
	}
	
	@Test
	public void TestCalcNonMemberDiscount() {
		ShoppingCart discCart = new ShoppingCart();
		
		//Test with 9 items
		discCart.AddItem("avocado", 9);
		r.CalcPurchasePrice(discCart, noMemNoEx);
		assertEquals(8.46f,r.GetBDNetTotal().floatValue(), 0);
		
		
		//Test with 10 items
		discCart.AddItem("avocado");
		r.CalcPurchasePrice(discCart, noMemNoEx);
		assertEquals(8.91f,r.GetBDNetTotal().floatValue(), 0);
		
		
		//Test with 11 items
		discCart.AddItem("avocado");
		r.CalcPurchasePrice(discCart, noMemNoEx);
		assertEquals(9.80f,r.GetBDNetTotal().floatValue(), 0);
	}
	@Test
	public void TestCalcMemberDiscount() {
		ShoppingCart discCart = new ShoppingCart();
		
		//Test with 4 items
		discCart.AddItem("avocado", 4);
		r.CalcPurchasePrice(discCart, memNoEx);
		assertEquals(3.56f,r.GetBDNetTotal().floatValue(), 0);
		
		//Test with 5 items
		discCart.AddItem("avocado");
		r.CalcPurchasePrice(discCart, memNoEx);
		assertEquals(4.45f,r.GetBDNetTotal().floatValue(), 0);
		
		//Test with 6 items
		discCart.AddItem("avocado"); //5.94
		r.CalcPurchasePrice(discCart, memNoEx);
		assertEquals(5.08f,r.GetBDNetTotal().floatValue(), 0);	
	}
	
	@Test
	public void TestTaxTotal () {
		ShoppingCart taxCart = new ShoppingCart();
		
		taxCart.AddItem("milk", 5); //11.45
		
		//testing tax exempt
		r.CalcPurchasePrice(taxCart, noMemEx);
		assertEquals(11.45f, r.BDGetAftTaxTotal().floatValue(), 0);
		
		//testing non-tax-exempt
		r.CalcPurchasePrice(taxCart, noMemNoEx);
		assertEquals(11.97f, r.BDGetAftTaxTotal().floatValue(), 0);
		
	}
	
	@Test
	public void TestPrint() {
		cart.AddItem("banana", 6);
		r.CalcPurchasePrice(cart, noMemNoEx);
		r.PrintItems(cart);
		r.PrintDisplay();
	}
	
}