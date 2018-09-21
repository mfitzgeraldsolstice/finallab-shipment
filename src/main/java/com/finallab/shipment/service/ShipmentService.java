package com.finallab.shipment.service;

import com.finallab.shipment.model.Shipment;
import com.finallab.shipment.repository.ShipmentRepository;
import com.finallab.shipment.summary.OrderLineItemsResult;
import com.finallab.shipment.summary.OrderResult;
import com.finallab.shipment.summary.ShipmentDetails;
import com.finallab.shipment.summary.ShipmentSummary;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ShipmentService {

    ShipmentRepository shipmentRepository;
    OrderService orderService;
    OrderLineItemsService orderLineItemsService;

    ShipmentService(ShipmentRepository shipmentRepository, OrderService orderService,
                    OrderLineItemsService orderLineItemsService){
        this.shipmentRepository = shipmentRepository;
        this.orderService = orderService;
        this.orderLineItemsService = orderLineItemsService;
    }

    public Shipment get(Long id){
        Shipment result = shipmentRepository.getShipmentById(id);
        return result;
    }

    public List<Shipment> getShipmentsByAccountId(Long id){
        List<Shipment> result = shipmentRepository.getShipmentsByAccountId(id);
        return result;
    }

    public Shipment save(Shipment shipment){
        Shipment savedShipment = shipmentRepository.save(shipment);
        OrderLineItemsResult itemToUpdate = orderLineItemsService.getOrderLineItemsById(shipment.getOrderLineItem());
        if(itemToUpdate != null){
            itemToUpdate.setShipmentId(shipment.getId());
            itemToUpdate.setId(itemToUpdate.getId());
            OrderLineItemsResult updatedLineItem = orderLineItemsService.updateOrderLineItem(itemToUpdate);
            System.out.println(updatedLineItem);
        }
        return savedShipment;
    }

    public void delete(Shipment shipment){
        OrderLineItemsResult orderLineItemToUpdate =
                orderLineItemsService.getOrderLineItemsById(shipment.getOrderLineItem());
        orderLineItemToUpdate.setShipmentId(null);

        shipmentRepository.delete(shipment);

    }

    public List<ShipmentDetails> getShipmentDetailsForAccount(Long accountId){
        List<Shipment> shipments = shipmentRepository.getShipmentsByAccountId(accountId);
        List<OrderResult> orders = orderService.getOrdersForAccount(accountId);
        HashMap<Long, OrderLineItemsResult> orderLineItems = new HashMap<>();
        List<OrderLineItemsResult> lineItemsList = new ArrayList<>();
        List<ShipmentDetails> results = new ArrayList<>();


        for(int i = 0; i < shipments.size(); i++){
            Long tempId = shipments.get(i).getOrderLineItem();
            OrderLineItemsResult temp = orderLineItemsService.getOrderLineItemsById(shipments.get(i).getOrderLineItem());
            orderLineItems.put(shipments.get(i).getOrderLineItem(), temp);
            lineItemsList.add(temp);

            ShipmentDetails tempShipment = new ShipmentDetails();
            if(temp.getOrderId() != null){
                tempShipment.setOrderNumber(temp.getOrderId());

            }
            //tempShipment.setOrderNumber(temp.getOrderId());
            tempShipment.setDeliveryDate(shipments.get(i).getDeliveryDate());
            tempShipment.setShippedDate(shipments.get(i).getShippedDate());
            tempShipment.setOrderLineItem(temp);

            results.add(tempShipment);

        }

        return results;

    }

}
