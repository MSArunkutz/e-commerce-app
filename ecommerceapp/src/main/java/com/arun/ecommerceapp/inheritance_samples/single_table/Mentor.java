package com.arun.ecommerceapp.inheritance_samples.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_mentor")
@DiscriminatorValue("3")
public class Mentor extends User {
    private double averageRating;
}
