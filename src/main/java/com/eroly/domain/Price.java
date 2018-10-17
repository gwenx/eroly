package com.eroly.domain;

import java.util.Date;

public class Price {
    private Integer priceId;

    private Double priceTotal;

    private Double priceDiscount;

    private Double priceDeposit;

    private Date priceDiedline;

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public Double getPriceDeposit() {
        return priceDeposit;
    }

    public void setPriceDeposit(Double priceDeposit) {
        this.priceDeposit = priceDeposit;
    }

    public Date getPriceDiedline() {
        return priceDiedline;
    }

    public void setPriceDiedline(Date priceDiedline) {
        this.priceDiedline = priceDiedline;
    }
}