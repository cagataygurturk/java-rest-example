package com.cagataygurturk.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class Sum implements Serializable {

    protected double sum;

    public Sum(double sum) {
        this.sum = sum;
    }

    @JsonProperty("sum")
    public double getSum() {
        return sum;
    }
}
