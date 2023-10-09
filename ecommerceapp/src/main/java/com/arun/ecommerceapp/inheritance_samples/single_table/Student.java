package com.arun.ecommerceapp.inheritance_samples.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_student")
@DiscriminatorValue("2")
public class Student extends User {
    private double psp;
    private double attendance;
}
