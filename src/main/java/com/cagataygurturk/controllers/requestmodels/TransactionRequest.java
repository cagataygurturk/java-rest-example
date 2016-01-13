package com.cagataygurturk.controllers.requestmodels;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionRequest {

    @JsonProperty("amount")
    public double amount;

    @JsonProperty("type")
    public String type;

    @JsonProperty("parent_id")
    public Long parentId;

}
