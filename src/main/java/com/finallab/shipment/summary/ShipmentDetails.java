package com.finallab.shipment.summary;

import java.util.Calendar;
import java.util.Date;

public class ShipmentDetails {

    private Long id;
    private Long orderNumber;
    private Calendar shippedDate;
    private Calendar deliveryDate;
    private OrderLineItemsResult orderLineItem;

    public ShipmentDetails() {
    }

    public ShipmentDetails(Long id, Long orderNumber, Calendar shippedDate, Calendar deliveryDate, OrderLineItemsResult orderLineItem) {
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

    public Calendar getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Calendar shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderLineItemsResult getOrderLineItem() {
        return orderLineItem;
    }

    public void setOrderLineItem(OrderLineItemsResult orderLineItem) {
        this.orderLineItem = orderLineItem;
    }
}
