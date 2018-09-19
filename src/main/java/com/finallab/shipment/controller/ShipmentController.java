package com.finallab.shipment.controller;

import com.finallab.shipment.model.Shipment;
import com.finallab.shipment.service.ShipmentService;
import com.finallab.shipment.summary.ShipmentDetails;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class ShipmentController {

    Logger logger = LoggerFactory.getLogger("ShipmentController");

    ShipmentService shipmentService;

    ShipmentController(ShipmentService shipmentService){
        this.shipmentService = shipmentService;
    }

    @PostMapping("/shipments")
    @HystrixCommand(fallbackMethod = "saveShipmentFallback")
    public Shipment save(@RequestBody Shipment shipment){
        Shipment savedShipment = shipmentService.save(shipment);
        return savedShipment;
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getShipmentFallback")
    public Shipment get(@PathVariable("id") Long id){
        Shipment result = shipmentService.get(id);
        return result;
    }

    @GetMapping("/account/{accountId}")
    @HystrixCommand(fallbackMethod = "getShipmentByAccountIdFallback")
    public List<Shipment> getShipmentsByAccountId(@PathVariable("accountId") Long id){
        List<Shipment> result = shipmentService.getShipmentsByAccountId(id);
        return result;
    }

    @PutMapping("/update")
    @HystrixCommand(fallbackMethod = "updateShipmentFallback")
    public Shipment update(@RequestBody Shipment shipment){
        Shipment updatedShipment = shipmentService.save(shipment);
        return updatedShipment;
    }

    @DeleteMapping("/delete")
    @HystrixCommand(fallbackMethod = "deleteShipmentFallback")
    public void delete(@RequestBody Shipment shipment){
        shipmentService.delete(shipment);
    }

    @GetMapping("/shipments/{accountId}")
    @HystrixCommand(fallbackMethod = "getShipmentDetailsFallback")
    public List<ShipmentDetails> getShipmentDetailsForAccount(@PathVariable("accountId") Long accountId){
        List<ShipmentDetails> results = shipmentService.getShipmentDetailsForAccount(accountId);
        return results;
    }

    public Shipment saveShipmentFallback(Shipment shipment){
        logger.error("Error saving shipment: " + shipment);
        return new Shipment();
    }

    public Shipment getShipmentFallback(Shipment shipment){
        logger.error("Error getting shipment: " + shipment);
        return new Shipment();
    }

    public Shipment getShipmentByAccountIdFallback(Shipment shipment){
        logger.error("Error getting shipment by account id: " + shipment);
        return new Shipment();
    }

    public Shipment updateShipmentFallback(Shipment shipment){
        logger.error("Error updating shipment: " + shipment);
        return new Shipment();
    }

    public Shipment deleteShipmentFallback(Shipment shipment){
        logger.error("Error deleting shipment: " + shipment);
        return new Shipment();
    }

    public List<ShipmentDetails> getShipmentDetailsFallback(List<ShipmentDetails> shipmentDetails){
        logger.error("Error getting shipment details: " + shipmentDetails);
        return new ArrayList<>();
    }

}
