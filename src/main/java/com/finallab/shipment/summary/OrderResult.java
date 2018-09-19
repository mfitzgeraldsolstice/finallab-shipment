package com.finallab.shipment.summary;

import java.util.Date;

public class OrderResult {

    private Long accountId;
    private Long orderNumber;
    private Date orderDate;
    private Long shippingAddressId;


    public OrderResult() {
    }

    public OrderResult(Long accountId, Long orderNumber, Date orderDate, Long shippingAddressId) {
        this.accountId = accountId;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.shippingAddressId = shippingAddressId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }
}
