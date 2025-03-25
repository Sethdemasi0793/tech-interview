package com.example.tech.interview.dao.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    @Column(nullable = false)
    private String accountType;

    private Customer customer;

    private boolean individualAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
        if (accountType.equals("INDIVIDUAL"))
            this.individualAccount = true;
        else
            this.individualAccount = false;
    }

    public boolean isIndividualAccount() {
        return individualAccount;
    }

    public void setIndividualAccount(boolean individualAccount) {
        this.individualAccount = individualAccount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
