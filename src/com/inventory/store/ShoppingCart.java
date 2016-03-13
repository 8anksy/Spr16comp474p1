package com.inventory.store;
import java.util.ArrayList;


public class ShoppingCart {
	private int MAX_CART_SIZE = 50;
	private int size = 0;
	private ArrayList<String> items;
	
	//Constructor that instantiates cart with an empty list
	public ShoppingCart() {
		this.items = new ArrayList<String>();
	}
	
	//Constructor that instantiates cart with a list of items
	public ShoppingCart(ArrayList<String> items) {
		this.items = items;
	}
	
	public void AddItem(String itemID) {
		if (size == MAX_CART_SIZE) {
			return;
		}else {
			items.add(itemID);
			size++;
		}
	}
	
	public void AddItem(String itemID, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.AddItem(itemID);
		}
	}
	
	public void AddItem(ArrayList<String> items) {
		for (String id : items) {
			this.AddItem(id);
		}			
	}
	
	public ArrayList<String> GetItems() {
		return this.items;
	}
	public void EmptyCart() {
		items.clear();
	}
	public void RemoveItem(String item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) == item) {
				size--;
			}
			
		}
	}
	
	public int GetSize() {
		return size;
	}
}
