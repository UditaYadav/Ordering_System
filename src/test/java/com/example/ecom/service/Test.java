package com.example.ecom.service;

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
import org.hibernate.cache.spi.support.SimpleTimestamper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;


public class Test {
    @InjectMocks
    @MockBean
    private OrderService orderService;

    @Mock
    private orderRepositoy orderRepo;

    @Mock
    private itemRepository itemRepo;
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
        Mockito.when(orderRepo.findAll()).thenReturn(orders1);
        assertEquals(3, orders1.get(0).getOrderId());

    }

    @org.junit.jupiter.api.Test
    public void testAddOrders() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {
        });

        when(orderRepo.save(orders1.get(0))).thenReturn(orders1.get(0));
        orders = orderService.addOrder(orders);
        assertEquals(3, orders.getOrderId());

    }

    @org.junit.jupiter.api.Test
    public void testdeleteOrder() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {


        });

        when(orderRepo.findByOrderId(orders.get(0).getOrderId())).thenReturn(orders.get(0));

        assertEquals(3L, orders.get(0).getOrderId());

    }

    //    @org.junit.jupiter.api.Test
//    public void testdeleteOrder() throws JsonProcessingException {
//
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.registerModule(new JavaTimeModule());
//
//        List<Orders>orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {
//
//
//        });
//        when(orderRepo.findByOrderId(orders.get(0).getOrderId())).thenReturn(orders.get(0));
//
//        assertEquals(3, orders.get(0).getOrderId());
//
//    }
    @org.junit.jupiter.api.Test
    public void testGetOrderById() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });

        Long id = 3L;
        when(orderRepo.findByOrderId(orders.get(0).getOrderId())).thenReturn(orders.get(0));
//
//        Orders retrievedOrder = orderService.getOrderById(orders.get(0).getOrderId());
        Orders retrievedOrder = orderService.getOrderById(id);
//        when(orderRepo.findByOrderId(orders.get(0).getOrderId())).thenReturn(orders.get(0));
//
//        Orders retrievedOrder = orderService.getOrderById(orders.get(0).getOrderId());
//
        assertEquals(id, retrievedOrder.getOrderId());

    }


    @org.junit.jupiter.api.Test
    public void testGetALlOrders() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });

        when(orderRepo.findAll()).thenReturn(orders);
        List<Orders> expected = orderService.getAllOrders();

        assertEquals(expected, orders);

    }

//    @org.junit.jupiter.api.Test
//    public void testGetALlOrders ()  throws JsonProcessingException {
//
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.registerModule(new JavaTimeModule());
//
//        List<Orders> orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {
//
//        });
//
//        when(orderRepo.findByOrderId(orders.get(0).getOrderId())).thenReturn(orders.get(0));
//
//        assertEquals(3, orders.get(0).getOrderId());
//
//    }


    @org.junit.jupiter.api.Test
    public void testUpdateOrderswithitem() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });
        Orders updatedOrder = new Orders();
        updatedOrder.setClientName("UpdatedClient");
        updatedOrder.setOrderId(1L);
        if (orders.isEmpty()) {
            when(orderRepo.findById(eq(updatedOrder.getOrderId()))).thenReturn(Optional.of(orders.get(0)));
            orderService.updateOrder(updatedOrder, updatedOrder.getOrderId());

            assertEquals("UpdatedClient", updatedOrder.getClientName());
        }

    }

    @org.junit.jupiter.api.Test

    public void testAdditem() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {
        });

        Long orderId = 1L;
        Orders mockOrder = new Orders();
        mockOrder.setOrderId(orderId);
        Set<Items> items = new HashSet<>();
        Items newItem = new Items();
        newItem.setItemName("New Item");
        newItem.setItemQuantity(1);
        newItem.setItemPrice(10);
        items.add(newItem);
        when(orderRepo.findByOrderId(orderId)).thenReturn(mockOrder);
        when(orderRepo.save(mockOrder)).thenReturn(mockOrder);
        Set<Items> result = orderService.createitem(items, orderId);
        verify(orderRepo).findByOrderId(orderId);
        verify(orderRepo).save(mockOrder);
        assertEquals(items, result);
    }


    @org.junit.jupiter.api.Test
    public void testGeItemById() throws JsonProcessingException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> order1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });

        when(itemRepo.findAll()).thenReturn(orders.getItemList().stream().toList());
        List<Items> result = orderService.getItemListById(items.getItemId());
        assertEquals(1, result.size());
        assertEquals(items.getItemId(), result.get(0).getItemId());
    }

    @org.junit.jupiter.api.Test
    public void testUpdatestatus() throws JsonProcessingException, IllegalArgumentException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> order1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });
        Long orderIdToUpdate = 3L;
        String statusSet = "CANCELLED";

        Orders mockOrder = new Orders();
        mockOrder.setOrderId(orderIdToUpdate);
        when(orderRepo.findByOrderId(orders.getOrderId())).thenReturn(mockOrder);
        String result = orderService.showStatus(orders.getOrderId(), statusSet);
        assertEquals("update", result);
        verify(orderRepo).findByOrderId(orders.getOrderId());
        assertEquals(StatuEnum.CANCELLED, mockOrder.getStatusss());
        verify(orderRepo).save(mockOrder);
        verifyNoMoreInteractions(orderRepo);
    }
    @org.junit.jupiter.api.Test
    public void testUpdateItemList() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> order1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });
        Long itemId = 1L;
        Items item = new Items();
        item.setItemId(itemId);
        item.setItemName("UpdatedItem");
        item.setItemPrice(30.0f);
        item.setItemQuantity(5);
        when(itemRepo.save(any(Items.class))).thenReturn(item);
        Items updatedItem = orderService.updateItemOfList(itemId, item);
        verify(itemRepo).save(item);
        assertEquals("UpdatedItem", updatedItem.getItemName());
        assertEquals(30.0f, updatedItem.getItemPrice(), 0.01);
        assertEquals(5, updatedItem.getItemQuantity());
        verifyNoMoreInteractions(itemRepo);
    }
    @org.junit.jupiter.api.Test
    public void testGetAllItems() throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());

        List<Orders> orders1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {

        });
        Mockito.when(itemRepo.findAll()).thenReturn(orders.getItemList().stream().toList());
        assertEquals(2L, items.getItemId());

    }
//    @org.junit.jupiter.api.Test
//    public void testDeleteItemById() throws Exception{
//
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.registerModule(new JavaTimeModule());
//
//        List<Orders> order1 = objectMapper.readValue(stringConverter, new TypeReference<List<Orders>>() {
//
//        });
//
//        doNothing().when(itemRepo).deleteById(anyLong());
//
//
//        String result = orderService.deleteOrderWithItems(2L);
//
//        assertEquals("Deleted", result);
//
//
//        verify(itemRepo).deleteById(2L);
//    }


}





































