package com.finallab.shipment.summary;

import java.util.Date;

public class ShipmentDetails {

    private Long id;
    private Long orderNumber;
    private Date shippedDate;
    private Date deliveryDate;
    private OrderLineItemsResult orderLineItem;

    public ShipmentDetails() {
    }

    public ShipmentDetails(Long id, Long orderNumber, Date shippedDate, Date deliveryDate, OrderLineItemsResult orderLineItem) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
        this.orderLineItem = orderLineItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderLineItemsResult getOrderLineItem() {
        return orderLineItem;
    }

    public void setOrderLineItem(OrderLineItemsResult orderLineItem) {
        this.orderLineItem = orderLineItem;
    }
}
