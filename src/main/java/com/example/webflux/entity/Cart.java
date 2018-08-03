package com.example.webflux.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItemList() {
        return this.items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
    
    public boolean removeItem(Item itm) { 
        return this.items.remove(itm);
    }
}
