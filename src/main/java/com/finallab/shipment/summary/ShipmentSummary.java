package com.finallab.shipment.summary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ShipmentSummary {

    @JsonProperty("account_id")
    private Long account_id;
    @JsonProperty("order_number")
    private Long order_number;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("shipment_date")
    private Date shipment_date;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("delivery_date")
    private Date delivery_date;
    @JsonProperty("product_name")
    private String product_name;
    @JsonProperty("product_quantity")
    private Double product_quantity;

    public ShipmentSummary(Long account_id, Long order_number, Date shipment_date, Date delivery_date, String product_name, Double product_quantity) {
        this.account_id = account_id;
        this.order_number = order_number;
        this.shipment_date = shipment_date;
        this.delivery_date = delivery_date;
        this.product_name = product_name;
        this.product_quantity = product_quantity;
    }

    public ShipmentSummary() {
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Long order_number) {
        this.order_number = order_number;
    }

    public Date getShipment_date() {
        return shipment_date;
    }

    public void setShipment_date(Date shipment_date) {
        this.shipment_date = shipment_date;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(Double product_quantity) {
        this.product_quantity = product_quantity;
    }
}
