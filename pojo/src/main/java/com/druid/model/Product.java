package com.druid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by druid on 20/02/17.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Product() {
        super();
    }

    public Product(Long id,String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
