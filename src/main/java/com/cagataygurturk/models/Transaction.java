package com.cagataygurturk.models;

import com.cagataygurturk.storage.Storable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class Transaction implements Serializable, Storable {

    protected long id;

    protected double amount;

    protected String type;

    protected long parentId;

    public Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public Transaction(double amount, String type, Transaction parent) {
        this(amount, type);
        this.parentId = parent.getId();
    }

    @JsonProperty("transaction_id")
    public long getId() {
        return id;
    }

    public Transaction setId(long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }

    public Transaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public Transaction setType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("parent_id")
    public Long getParentId() {

        if (parentId == 0) {
            return null;
        }

        return parentId;
    }


    public Transaction setParent(Transaction parent) {
        this.parentId = parent.getId();
        return this;
    }

    @JsonIgnore
    public long getGeneratedId() {
        return this.getId();
    }

    public void setGeneratedId(long id) {
        this.id = id;
    }
}
