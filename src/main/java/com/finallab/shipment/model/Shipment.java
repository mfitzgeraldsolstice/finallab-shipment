package com.finallab.shipment.model;

import com.finallab.shipment.summary.AccountResult;
import com.finallab.shipment.summary.AddressResult;
import com.finallab.shipment.summary.OrderLineItemsResult;
import com.finallab.shipment.summary.ShipmentSummary;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Shipment")
@SqlResultSetMapping(
        name = "ShipmentSummary",
        classes = @ConstructorResult(
                targetClass = ShipmentSummary.class,
                columns = {
                        //@ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "account_id", type = Long.class),
                        @ColumnResult(name = "order_number", type = Long.class),
                        @ColumnResult(name = "shipped_date", type = Timestamp.class),
                        @ColumnResult(name = "delivery_date", type = Timestamp.class),
                        @ColumnResult(name = "product_name", type = String.class),
                        @ColumnResult(name = "product_quantity", type = Double.class)

                }))
@NamedNativeQueries(value = {
        @NamedNativeQuery(
                resultSetMapping = "ShipmentSummary",
                name = "Shipment.getShipmentSummary",
                query = "select distinct o.account_id, o.order_number, sh.shipped_date, sh.delivery_date, p.name as product_name, i.quantity as product_quantity from orders o inner join shipment sh on sh.account_id = o.account_id inner join order_line_items i on i.order_id = o.id inner join product p on p.id = i.product_id where o.account_id = :id order by sh.delivery_date;"
        )
})
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@JsonBackReference(value = "account")
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Long accountId;
    //@JsonBackReference(value = "shippingAddress")
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Long shippingAddressId;
    //@JsonBackReference(value = "orderLineItems")
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "shipment_id")
    private Long orderLineItem;

    private Calendar shippedDate;
    private Calendar deliveryDate;

    public Shipment() {
    }

    public Shipment(Long id, Long accountId, Long shippingAddressId, Long orderLineItem, Calendar shippedDate, Calendar deliveryDate) {
        this.id = id;
        this.accountId = accountId;
        this.shippingAddressId = shippingAddressId;
        this.orderLineItem = orderLineItem;
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccount(Long accountId) {
        this.accountId = accountId;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getOrderLineItem() {
        return orderLineItem;
    }

    public void setOrderLineItems(Long orderLineItem) {

        this.orderLineItem = orderLineItem;
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
}
