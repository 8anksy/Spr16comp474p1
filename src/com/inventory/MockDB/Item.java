package com.inventory.MockDB;

public class Item {
	private String id;
	private float cost;
	
	public Item(String id, float cost) {
		this.id = id;
		this.cost = cost;
	}
	
	public String GetID() {
		return id;
	}
	public float GetCost() {
		return cost;
	}
	@Override
	public String toString() {
		return "MockItem [id=" + id + ", cost=" + cost + "]";
	}
}
