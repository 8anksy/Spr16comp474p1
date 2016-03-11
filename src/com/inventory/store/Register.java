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
	
	public Register(ItemDO database){
		this.database = database;
	}
	
	public void CalcPurchasePrice (ShoppingCart cart, Customer customer) {
		CalcRawTotal(cart);
		CalcDiscount(cart, customer);
		CalcTaxTotal(customer);
	}
	
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
		//rawTotal = RoundNearestCent(total);
	}
	
	private void CalcDiscount (ShoppingCart cart, Customer customer) {
		float runningTotal = bdRawTotal.floatValue();
		float netDisc = 0.0f;
		if (customer.GetMemberStatus()) {
			netDisc = bdRawTotal.floatValue() * MEMBER_DISC;
		}
		if (cart.GetSize() >= 10) {
			netDisc = netDisc + bdRawTotal.floatValue() * TEN_OR_MORE;
			
		}
		if (cart.GetSize() > 5 && cart.GetSize() < 10) {
			netDisc = netDisc + bdRawTotal.floatValue() * OVER_FIVE;
		}
		runningTotal = bdRawTotal.floatValue() - netDisc;
		bdNetDisc = new BigDecimal(netDisc).setScale(2,BigDecimal.ROUND_HALF_EVEN);
		bdNetTotal = new BigDecimal(runningTotal).setScale(2,BigDecimal.ROUND_HALF_EVEN);
		
	}
	
	private void CalcTaxTotal (Customer customer) {
		if (customer.GetTaxStatus()) {
			bdTaxAmount = new BigDecimal(0.00);
			bdAftTaxTotal = new BigDecimal(bdNetTotal.floatValue()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
		else {
			bdTaxAmount = new BigDecimal(bdNetTotal.floatValue() * TAX_RATE).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			float aftTaxTotal = bdNetTotal.floatValue() + bdTaxAmount.floatValue();
			bdAftTaxTotal = new BigDecimal(aftTaxTotal).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	
	public void PrintItems(ShoppingCart cart) {
		for (String i : cart.GetItems()) {
			if (database.Contains(i)) {
				System.out.println(i + database.GetItem(i).GetCost());
			}
		}
	}
	public void PrintDisplay() {
		//TODO print all totals/values
		
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
