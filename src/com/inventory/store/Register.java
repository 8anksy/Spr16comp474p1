package com.inventory.store;

import java.math.BigDecimal;

import com.inventory.MockDB.ItemDO;

public class Register {
	private float MEMBER_DISC = 0.10f;
	private float TEN_OR_MORE = 0.10f;
	private float OVER_FIVE = 0.05f;
	private float TAX_RATE = 0.045f;
//	private float rawTotal;
//	private float netTotal;
//	private float taxAmount;
//	private float aftTaxTotal;
	private ItemDO database;
	
	
	private BigDecimal bdRawTotal;
	private BigDecimal bdNetTotal;
	private BigDecimal bdNetDisc;
	private BigDecimal bdTaxAmount;
	private BigDecimal bdAftTaxTotal;
	
	// @param database The appropriate database to be used with this Register
	// @return Register Constructs a new register with the linked database.
	public Register(ItemDO database){
		this.database = database;
	}
	
	/* This is an interface method which calls several private methods
	 * that calculate different parts of the total.
	 * @param cart A shopping cart with any number of items.
	 * @param customer the Customer being paired with the cart.
	 */
	public void CalcPurchasePrice (ShoppingCart cart, Customer customer) {
		CalcRawTotal(cart);
		CalcDiscount(cart.GetSize(), customer.GetMemberStatus());
		CalcTaxTotal(customer.GetTaxStatus());
	}
	
	//@param cart The shopping cart used to calculate the base total.
	private void CalcRawTotal (ShoppingCart cart) {
		float total = 0;
		for (String id : cart.GetItems()) {
			if (database.Contains(id)) {
				total += database.GetItem(id).GetCost();
			}
			else {
				cart.RemoveItem(id);
			}
		}
		bdRawTotal = new BigDecimal(total).setScale(2,BigDecimal.ROUND_HALF_EVEN);
	}
	
	// @param cartSize The number of items in the cart
	// @param memStatus The membership status of the customer
	private void CalcDiscount (int cartSize, boolean memStatus) {
		float runningTotal = bdRawTotal.floatValue();
		float netDisc = 0.0f;
		if (memStatus) {
			netDisc = bdRawTotal.floatValue() * MEMBER_DISC;
		}
		if (cartSize >= 10) {
			netDisc = netDisc + bdRawTotal.floatValue() * TEN_OR_MORE;
			
		}
		if (cartSize > 5 && cartSize < 10) {
			netDisc = netDisc + bdRawTotal.floatValue() * OVER_FIVE;
		}
		runningTotal = bdRawTotal.floatValue() - netDisc;
		bdNetDisc = new BigDecimal(netDisc).setScale(2,BigDecimal.ROUND_HALF_EVEN);
		bdNetTotal = new BigDecimal(runningTotal).setScale(2,BigDecimal.ROUND_HALF_EVEN);
		
	}
	
	//@param taxStatus The exemption status of the customer
	private void CalcTaxTotal (boolean taxStatus) {
		if (taxStatus) {
			bdTaxAmount = new BigDecimal(0.00);
			bdAftTaxTotal = new BigDecimal(bdNetTotal.floatValue()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
		else {
			bdTaxAmount = new BigDecimal(bdNetTotal.floatValue() * TAX_RATE).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			float aftTaxTotal = bdNetTotal.floatValue() + bdTaxAmount.floatValue();
			bdAftTaxTotal = new BigDecimal(aftTaxTotal).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	
	//@param cart The items used to print their ids and values.
	public void PrintItems(ShoppingCart cart) {
		for (String i : cart.GetItems()) {
			if (database.Contains(i)) {
				System.out.println(i + database.GetItem(i).GetCost());
			}
		}
	}
	
	public void PrintDisplay() {
		
		System.out.println("Subtotal    " + BDGetRawTotal());
		System.out.println("-----------------");
		System.out.println("Discount    -" + BDGetNetDiscount());
		System.out.println("Tax	     " + BDGetTaxAmount());
		System.out.println("=================");
		System.out.println("Total	    " + BDGetAftTaxTotal());
	}
	
	
	
	public BigDecimal BDGetRawTotal() {
		return bdRawTotal;
	}
	public BigDecimal GetBDNetTotal() {
		return bdNetTotal;
	}
	public BigDecimal BDGetTaxAmount() {
		return bdTaxAmount;
	}
	public BigDecimal BDGetAftTaxTotal() {
		return bdAftTaxTotal;
	}
	public BigDecimal BDGetNetDiscount() {
		return bdNetDisc;
	}
}
