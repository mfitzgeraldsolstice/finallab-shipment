package com.finallab.shipment.service;

import com.finallab.shipment.summary.OrderLineItemsResult;
import com.finallab.shipment.summary.OrderResult;
import org.hibernate.criterion.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@Component
@FeignClient(name="order-service")
public interface OrderLineItemsService {

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    OrderLineItemsResult getOrderLineItemsById(@PathVariable("id") Long id);

    @PutMapping(value="/update")
    OrderLineItemsResult updateOrderLineItem(@RequestBody OrderLineItemsResult orderLineItemsResult);

    @DeleteMapping(value = "/delete/{id}")
    void delete(@PathVariable("id") Long orderLineItemId);

//    @RequestMapping(method = RequestMethod.GET, value="/orders/{accountId}")
//    List<OrderLineItemsResult> getOrderLineItemsByIForAccount(@PathVariable("accountId") Long accountId);
}
