package com.finallab.shipment.service;

import com.finallab.shipment.summary.OrderResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Path;
import java.util.List;

@Component
@FeignClient(name="order-service")
public interface OrderService {

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    OrderResult getOrder(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value="/orders/{accountId}")
    List<OrderResult> getOrdersForAccount(@PathVariable("accountId") Long accountId);
}
