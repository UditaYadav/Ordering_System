package com.example.ecom.service;

import com.example.ecom.controller.EcommerenceController;
import com.example.ecom.entity.Items;
import com.example.ecom.entity.Orders;
import com.example.ecom.entity.StatuEnum;
import com.example.ecom.repo.itemRepository;
import com.example.ecom.repo.orderRepositoy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ControllercTest {


    @Mock
    private OrderService orderService;
 @InjectMocks
    private EcommerenceController ecommerenceController;
    @Spy
    ObjectMapper objectMapper;
    @Mock
    private Orders orders;
    @Mock
    private Items items;

    private String stringConverter;

    private Set<Items> itemSet = new HashSet<>();


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        orders = new Orders();
        orders.setOrderId(3L);
        orders.setOrderDate(LocalDate.now());
        orders.setClientName("Client1");
        orders.setStatusss(StatuEnum.valueOf("PENDING"));
        orders.setClientAddress("clientAdress");
        orders.setNumberOfItems(2);
        orders.setDispatchDate("2023-09-17 21:24:17.222126");
        orders.setOrderAmount(10);
        orders.setNumberOfItems(1);
        orders.setCreatedAt(LocalDate.now());
        items = new Items();
        items.setItemId(2L);
        items.setItemName("item1");
        items.setItemQuantity(2);
        items.setItemPrice(20);
        items.setShippedDate("2023-09-17 21:24:17.222126");
        itemSet.add(items);
        orders.setItemList(itemSet);


        stringConverter = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("test.json"), StandardCharsets.UTF_8);
    }

    @org.junit.jupiter.api.Test
    public void testGetAllOrders() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });
        List<Orders> ordersList = new ArrayList<>();
        Orders order1 = new Orders();
        order1.setOrderId(2L);
        when(orderService.getAllOrders()).thenReturn(ordersList);
        ResponseEntity<List<Orders>> result = ecommerenceController.getAllOrders();
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @org.junit.jupiter.api.Test
    public void testGetOrderById() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {
        });

        Long orderId = 123L;
        Orders mockOrder = new Orders();
        mockOrder.setOrderId(orderId);
        when(orderService.getOrderById(orderId)).thenReturn(mockOrder);
        ResponseEntity<Orders> result = ecommerenceController.getOrderById(orderId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockOrder, result.getBody());
    }


    @org.junit.jupiter.api.Test
    public void testAddOrders() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {
        });

        Orders mockOrder = new Orders();
        mockOrder.setOrderId(1L);
        when(orderService.addOrder(any(Orders.class))).thenReturn(mockOrder);
        Orders result = ecommerenceController.addOrderr(new Orders());
        assertNotNull(result);

        assertEquals(mockOrder.getOrderId(), result.getOrderId());
    }


    @org.junit.jupiter.api.Test
    public void testdeleteOrder() throws Exception{

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {


        });
        when(orderService.deleteOrder(anyLong())).thenReturn(null);


        ResponseEntity<String> result = ecommerenceController.deleteOrderByID(123L);


        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("deleted", result.getBody());
    }

    @org.junit.jupiter.api.Test
    public void testUpdateOrderswithitem() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });

        Orders inputOrder = new Orders();
        inputOrder.setOrderId(123L);
        when(orderService.updateOrder(eq(inputOrder), anyLong())).thenReturn(inputOrder);
        ResponseEntity<Orders> result = ecommerenceController.updateOrder(inputOrder, 123L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(inputOrder, result.getBody());
    }
    @org.junit.jupiter.api.Test
    public void testGetAllItems() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });
        List<Items> mockItemsList = new ArrayList<>();
        Items item1 = new Items();
        item1.setItemId(1L);
        item1.setItemName("Item 1");
        mockItemsList.add(item1);
        when(orderService.getAllListOfItems()).thenReturn(mockItemsList);
        List<Items> result = ecommerenceController.getItemList();
        assertNotNull(result);
        assertEquals(mockItemsList, result);
    }


    @org.junit.jupiter.api.Test
    public void testGeItemById() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> order1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });
        Long orderId = 123L;
        List<Items> mockItemList = new ArrayList<>();

        Items item1 = new Items();
        item1.setItemId(1L);
        item1.setItemName("Item 1");
        mockItemList.add(item1);
        when(orderService.getItemListById(orderId)).thenReturn(mockItemList);
        List<Items> result = ecommerenceController.getListOfItemByIddd(orderId);
        assertNotNull(result);
        assertEquals(mockItemList, result);
    }

    @org.junit.jupiter.api.Test
    public void testDeleteItemById() throws Exception{

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> order1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });

        String result = ecommerenceController.deleteItemById(123L);
        assertEquals("Item is Deleted", result);
        verify(orderService).deleteOrderWithItems(123L);
    }
    @org.junit.jupiter.api.Test
    public void testCreateListOfItem() throws JsonProcessingException {

        Long orderId = 123L;
        Set<Items> mockItemsSet = new HashSet<>();
        Items item1 = new Items();
        item1.setItemId(1L);
        item1.setItemName("Item 1");
        mockItemsSet.add(item1);
        when(orderService.createitem(anySet(), eq(orderId))).thenReturn(mockItemsSet);
        Set<Items> result = ecommerenceController.createlistofitem(mockItemsSet, orderId);

        assertNotNull(result);
        assertEquals(mockItemsSet, result);
    }

    @org.junit.jupiter.api.Test
    public void testUpdateItemList() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> order1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });

        Long orderId = 123L;
        Items itemToUpdate = new Items();
        itemToUpdate.setItemId(orderId);
        itemToUpdate.setItemName("Updated Item Name");
        when(orderService.updateItemOfList(eq(orderId), any(Items.class))).thenReturn(itemToUpdate);
        Items result =ecommerenceController.updateilistofItem(orderId, itemToUpdate);
        assertNotNull(result);
        assertEquals(orderId, result.getItemId());
        assertEquals("Updated Item Name", result.getItemName());
    }
    @org.junit.jupiter.api.Test
    public void testUpdatestatus() throws JsonProcessingException, IllegalArgumentException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> order1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {
        });
        Long orderId = 123L;
        String updatedStatus = "NEW_STATUS";
        when(orderService.showStatus(eq(orderId), eq(updatedStatus))).thenReturn("updated");
        String result = ecommerenceController.updateStatus(orderId, updatedStatus);
        assertEquals("updated", result);
    }
    }













