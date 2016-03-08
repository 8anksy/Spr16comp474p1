package com.inventory.store;

import com.inventory.MockDB.ItemDO;

public class Register {
	private float MEMBER_DISC = 0.10f;
	private float TEN_OR_MORE = 0.10f;
	private float OVER_FIVE = 0.05f;
	private float TAX_RATE = 0.045f;
	private float rawTotal;
	private float netTotal;
	private float taxAmount;
	private float aftTaxTotal;
	private ItemDO database;
	
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
		}
		rawTotal = total;
	}
	
	private void CalcDiscount (ShoppingCart cart, Customer customer) {
		float runningTotal = rawTotal;
		if (customer.GetMemberStatus()) {
			runningTotal = rawTotal - (rawTotal * MEMBER_DISC);
		}
		if (cart.GetSize() >= 10) {
			runningTotal = runningTotal - (runningTotal * TEN_OR_MORE);
		}
		if (cart.GetSize() > 5) {
			runningTotal = runningTotal - (runningTotal * OVER_FIVE);
		}
		netTotal = RoundNearestCent(runningTotal);
	}
	
	private void CalcTaxTotal (Customer customer) {
		if (customer.GetTaxStatus()) {
			taxAmount = 0.0f;
		}
		else {
			taxAmount = RoundNearestCent(netTotal * TAX_RATE);
			aftTaxTotal = netTotal + taxAmount;
		}
	}
	
	public void PrintDisplay() {
		//TODO print all totals/values
	}
	
	//Helper method for rounding totals
	private float RoundNearestCent(float f) {
		float round = f * 100;
		Math.round(round);
		round = round/100;
		return round;
	}
	
	
	public float GetRawTotal() {
		return rawTotal;
	}
	public float GetNetTotal() {
		return netTotal;
	}
	public float GetTaxAmount() {
		return taxAmount;
	}
	public float GetAftTaxTotal() {
		return aftTaxTotal;
	}
}
