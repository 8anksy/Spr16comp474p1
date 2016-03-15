package com.inventory.store;
import java.util.ArrayList;


public class ShoppingCart {
	private int MAX_CART_SIZE = 50;
	private int size = 0;
	private ArrayList<String> items;
	
	/**
	 * Constructor that instantiates cart with an empty list
	 */
	public ShoppingCart() {
		this.items = new ArrayList<String>();
	}
	
	/**
	 * Constructor that instantiates cart with a list of items.
	 * @param items An ArrayList that contains items to be added to the cart.
	 */
	public ShoppingCart(ArrayList<String> items) {
		this.items = items;
	}
	
	/**
	 * This is the base method for adding new items to the shopping cart.
	 * @param itemID A String to be added to the cart. When added, the cart size will increase by 1.
	 */
	public void AddItem(String itemID) {
		if (size == MAX_CART_SIZE) {
			return;
		}else {
			items.add(itemID);
			size++;
		}
	}
	
	/**
	 * This is the base method for adding new items to the shopping cart.
	 * @param itemID A String to be added to the cart.
	 * @param quantity The number of itemID to add to the ShoppingCart.
	 */
	public void AddItem(String itemID, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.AddItem(itemID);
		}
	}
	
	/**
	 * This method is used for adding new items to the cart from an ArrayList.
	 * @param items An ArrayList of items to be added to the cart.
	 */
	public void AddItem(ArrayList<String> items) {
		for (String id : items) {
			this.AddItem(id);
		}			
	}
	
	/**
	 * @return ArrayList Returns the list of items in the ShoppingCart
	 */
	public ArrayList<String> GetItems() {
		return this.items;
	}
	/**
	 * Removes ALL items from the ShoppingCart
	 */
	public void EmptyCart() {
		items.clear();
	}
	/**
	 * Doesn't actually remove items from the ArrayList. However, it does reduce the size of the cart to reflect items not being counted.
	 * If the item for removal is found, only the size is decremented.
	 * @param item The item in question to be removed
	 */
	public void RemoveAll(String item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) == item) {
				size--;
			}
		}
	}
	public void RemoveQuantity(String item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			if (items.get(i) == item) {
				size --;
			}
		}
	}
	
	/**
	 * @return int Returns the size of the ShoppingCart.
	 */
	public int GetSize() {
		return size;
	}
}
