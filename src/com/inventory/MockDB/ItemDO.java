package com.inventory.MockDB;

import java.util.ArrayList;

/*
 * This class is a mock database that instantiates several MockItem objects.
 * The items are hard coded and will be stored in a linear array data structure.
 */
public class ItemDO {
	private ArrayList<Item> database;
	
	public ItemDO () {
		this.database = new ArrayList<Item>();
		
		Item item0 = new Item("banana", .19f);
		Item item1 = new Item("potato", .49f);
		Item item2 = new Item("avocado", .99f);
		Item item3 = new Item("Cheerios", 2.19f);
		Item item4 = new Item("eggs", 1.99f);
		Item item5 = new Item("milk", 2.29f);
		Item item6 = new Item("Ruffles", 1.49f);
		Item item7 = new Item("flour", 2.99f);
		Item item8 = new Item("sugar", 2.89f);
		Item item9 = new Item("chardonnay", 8.99f);
		
		database.add(item0);
		database.add(item1);
		database.add(item2);
		database.add(item3);
		database.add(item4);
		database.add(item5);
		database.add(item6);
		database.add(item7);
		database.add(item8);
		database.add(item9);
		}
	
	public Item GetItem(String itemID) {
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).GetID() == itemID) {
				return database.get(i);
			}
		}
		return null;
	}
	
	public boolean Contains(String itemID) {
		boolean contains = false;
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).GetID() == itemID) {
				contains = true;
			}
		}
		return contains;
	}
}
