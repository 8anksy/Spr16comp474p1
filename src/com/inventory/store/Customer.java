package com.inventory.store;

public class Customer {
	private boolean memberStatus;
	private boolean taxExempt;
	
	public Customer () {
		this(false, false);
	}
	public Customer (boolean memberStatus) {
		this(memberStatus, false);
	}
	public Customer (boolean memberStatus, boolean taxExempt) {
		this.memberStatus = memberStatus;
		this.taxExempt = taxExempt;
	}
	
	public boolean GetMemberStatus() {
		return memberStatus;
	}
	public boolean GetTaxStatus() {
		return taxExempt;
	}
}
