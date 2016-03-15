package com.inventory.MockDB;

/**
 * This class is a mock Item contains a String id and cost. It is used to populate the mock database. 
 */
public class Item {
	private String id;
	private float cost;
	
	
	/**
	 * @param id The name of the item in the database.
	 * @param cost The float value of the item being created
	 */
	public Item(String id, float cost) {
		this.id = id;
		this.cost = cost;
	}
	
	/**
	 * @return String The stored id of the Item
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * @return float The stored cost of the Item
	 */
	public float getCost() {
		return cost;
	}
	@Override
	/**
	 * @return String Returns the id and cost of the Item.
	 */
	public String toString() {
		return "MockItem [id=" + id + ", cost=" + cost + "]";
	}
}
