package com.example.store_front.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Category {

    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ":"+name;
    }
}
