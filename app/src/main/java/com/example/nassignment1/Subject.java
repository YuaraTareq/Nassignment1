package com.example.nassignment1;

import java.util.List;

public class Subject {
    private String name;
    private List<Item> items;

    public Subject(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }
}
