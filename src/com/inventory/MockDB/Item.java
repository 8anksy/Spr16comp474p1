package com.inventory.MockDB;

public class Item {
	private String id;
	private float cost;
	
	
	/* @param id The name of the item in the database.
	 * @param cost The float value of the item being created
	 * @return Item The constructed object
	 */
	public Item(String id, float cost) {
		this.id = id;
		this.cost = cost;
	}
	
	// @return String The stored id of the Item
	public String GetID() {
		return id;
	}
	// @return float The stored cost of the Item
	public float GetCost() {
		return cost;
	}
	@Override
	public String toString() {
		return "MockItem [id=" + id + ", cost=" + cost + "]";
	}
}
