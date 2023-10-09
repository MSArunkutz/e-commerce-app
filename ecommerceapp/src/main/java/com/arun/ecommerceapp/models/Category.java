package com.arun.ecommerceapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Category extends BaseModel{
    private String title;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
