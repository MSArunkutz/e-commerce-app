package com.arun.ecommerceapp.models;

import jakarta.persistence.Entity;

@Entity
public class Category extends BaseModel{
    private String title;
    private String description;
}
