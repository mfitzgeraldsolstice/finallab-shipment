package com.finallab.shipment.repository;

import com.finallab.shipment.model.Shipment;
import com.finallab.shipment.summary.ShipmentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Shipment getShipmentById(long id);

    @Query(nativeQuery = true)
    List<ShipmentSummary> getShipmentSummary(@Param("id") Long id);

    List<Shipment> getShipmentsByAccountId(@Param("id") Long id);
}
