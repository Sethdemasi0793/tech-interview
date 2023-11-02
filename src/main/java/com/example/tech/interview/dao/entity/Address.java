package com.example.tech.interview.dao.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {


    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

}
