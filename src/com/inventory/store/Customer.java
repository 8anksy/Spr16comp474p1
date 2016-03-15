package com.inventory.store;
/**
 * @version 1.0
 * This class contains the membership status and tax exemption status. They are represented
 * by boolean values.
 */
public class Customer {
	private boolean memberStatus;
	private boolean taxExempt;
	
	/**
	 * This constructor takes no parameters and sets memberStatus and taxExempt to the default values (false, false).
	 */
	public Customer () {
		this(false, false);
	}
	/**
	 * This constructor takes a single parameter and sets taxExemption to false.
	 * @param memberStatus Setting this value to 'true' means the customer is a member. Else, not a member.
	 */
	public Customer (boolean memberStatus) {
		this(memberStatus, false);
	}
	/**
	 * This constructor takes a single parameter and sets taxExemption to false.
	 * @param memberStatus Setting this value to 'true' means the customer is a member. Else, not a member.
	 * @param taxExempt Setting this value to 'true' means the customer is exempt from taxes. Else, not exempt.
	 */
	public Customer (boolean memberStatus, boolean taxExempt) {
		this.memberStatus = memberStatus;
		this.taxExempt = taxExempt;
	}
	
	/**
	 * @return boolean Returns 'true' if Customer is a member. Returns 'false' if not a member.
	 */
	public boolean GetMemberStatus() {
		return memberStatus;
	}
	/**
	 * @return boolean Returns 'true' if Customer is tax exempt. Returns 'false' if not tax exempt.
	 */
	public boolean GetTaxStatus() {
		return taxExempt;
	}
}
