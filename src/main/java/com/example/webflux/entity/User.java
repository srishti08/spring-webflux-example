package com.example.webflux.entity;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class User {

    @Id
    private String id;
    @Field
    private String username;
    @Field
    private String password;
    private Cart shoppingCart;

    public User(String username, String password, String id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.shoppingCart = new Cart();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Cart getShoppingCart() {
        return this.shoppingCart;
    }

}
