package com.finallab.shipment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finallab.shipment.controller.ShipmentController;
import com.finallab.shipment.model.Shipment;
import com.finallab.shipment.service.ShipmentService;
import com.finallab.shipment.summary.OrderLineItemsResult;
import com.finallab.shipment.summary.ShipmentDetails;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.*;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ShipmentController.class)
@AutoConfigureMockMvc(secure = false)
public class ShipmentControllerTests {

    @Resource
    WebApplicationContext webApplicationContext;
    @Autowired private MockMvc mockMvc;
    @MockBean private ShipmentService shipmentService;

    private ObjectMapper mapper;
    private Shipment shipment;
    private List<Shipment> shipmentList;
    private List<ShipmentDetails> shipmentDetails;
    //final Model model;

    @Before
    public void shipmentSetUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
        shipment = initializeTestShipment();
        shipmentList = initializeTestShipmentList();
        shipmentDetails = initializeTestShipmentDetails();
    }

    @Test
    public void testAddShipment() throws Exception {
        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);

        String requestJson = mapper.writeValueAsString(shipment);

        mockMvc.perform(post("/shipments", shipment)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void getShipmentByAccountId() throws Exception {
        when(shipmentService.getShipmentsByAccountId(1L)).thenReturn(shipmentList);

        mockMvc.perform(get("/shipments/{accountId}", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].accountId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].shippingAddressId", Matchers.is(2)));

        Mockito.verify(shipmentService, times(1)).getShipmentsByAccountId(1L);
    }

    @Test
    public void getShipmentDetailsForAccountTest() throws Exception {
        when(shipmentService.getShipmentDetailsForAccount(1L)).thenReturn(shipmentDetails);
        Calendar shippedDate = new GregorianCalendar(2018, Calendar.JUNE, 22);

        mockMvc.perform(get("/shipments/{accountId}/details", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].orderNumber", Matchers.is(4)));
                //.andExpect(jsonPath("$[0].shippedDate", Matchers.is(shippedDate)));

        Mockito.verify(shipmentService, times(1)).getShipmentDetailsForAccount(1L);
    }

    private Shipment initializeTestShipment(){
        Calendar shippedDate = new GregorianCalendar(2018, Calendar.JUNE, 22);
        Calendar deliveryDate = new GregorianCalendar(2018, Calendar.JUNE, 25);

        Shipment shipment = new Shipment(2L, 1L, 2L, 3L,
                shippedDate, deliveryDate);

        return shipment;
    }

    private List<ShipmentDetails> initializeTestShipmentDetails(){
        Calendar shippedDate = new GregorianCalendar(2018, Calendar.JUNE, 22);
        Calendar deliveryDate = new GregorianCalendar(2018, Calendar.JUNE, 25);
        OrderLineItemsResult orderLineItemsResult = new OrderLineItemsResult();

        ShipmentDetails shipmentDetails = new ShipmentDetails(1L, 4L, shippedDate,
                deliveryDate, orderLineItemsResult);

        List<ShipmentDetails> shipmentDetailsList =  new ArrayList<>();
        shipmentDetailsList.add(shipmentDetails);

        return shipmentDetailsList;
    }

    private List<Shipment> initializeTestShipmentList(){
        Calendar shippedDate = new GregorianCalendar(2018, Calendar.JUNE, 22);
        Calendar deliveryDate = new GregorianCalendar(2018, Calendar.JUNE, 25);

        Shipment shipment = new Shipment(2L, 1L, 2L, 3L,
                shippedDate, deliveryDate);

        //Shipment shipment1 = new Shipment(3L, 1L, 2L, 4L, shippedDate, deliveryDate);

        List<Shipment> testShipmentList = new ArrayList<>();
        testShipmentList.add(shipment);
        //testShipmentList.add(shipment1);
        return testShipmentList;
    }

}
