package com.finallab.shipment.summary;

public class OrderLineItemsResult {

    private Long id;
    private ProductResult product;
    private Long quantity;
    private Double price;
    private Double totalPrice;
    private Long orderId;
    private Long shipmentId;

    public OrderLineItemsResult() {
    }

    public OrderLineItemsResult(Long id, ProductResult product, Long quantity, Double price, Double totalPrice,
                                Long orderId, Long shipmentId) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.shipmentId = shipmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductResult getProduct() {
        return product;
    }

    public void setProduct(ProductResult product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }
}
