package com.example.ecom.service.integration.service;

import com.example.ecom.entity.Items;
import com.example.ecom.entity.Orders;
import com.example.ecom.repo.itemRepository;
import com.example.ecom.service.integration.REpo.OrderREpository;
import jakarta.persistence.criteria.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

public class ItemService {


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    ItemService itemService;
    @Mock
    OrderREpository orderRepo;
    @Mock
    itemRepository itemRepo;
    @Test
    void testGetAllListOfOrderedItems() {
        MockitoAnnotations.initMocks(this);
        Items item=new Items();
        List<Items> itemList=new ArrayList<>();
        item.setItemName("xyz");
        item.setItemPrice(5.0f);
        item.setItemId(1L);
        item.setItemQuantity(3);
        item.setShippedDate("2023-98-01");
        itemList.add(item);
        when(itemRepo.findAll()).thenReturn(itemList) ;
        itemService.testGetAllListOfOrderedItems();

    }
    @Test
    void testGetAllListOfOrderedItemsById(Long id) {
        MockitoAnnotations.initMocks(this);
        Items item=new Items();

        id=1L;
        List<Items> itemList=new ArrayList<>();
        item.setItemName("w");
        item.setItemPrice(5);
        item.setItemId(id);
        item.setItemQuantity(7);
//        item.setShippedDate("");
        itemList.add(item);
        when(itemRepo.findAll()).thenReturn(itemList) ;
        itemService.testGetAllListOfOrderedItemsById(id);

    }
//    @Test
//    void testUpdateExistingOrderItem()
//        MockitoAnnotations.intiMock(this);
//        Orders order=new Orders();
//
//        order.("abc");
//        //  order.setOrderDate(LocalDate.parse("20230801"));
//        order.setDispatchDate("4566666");
//        order.setOrderAmount(666);
//        order.setNumberOfItems(23);
//        Long orderId=1L;
//        ItemDTO itemDTO=new ItemDTO();
//        Item item=new Item();
//        Long itemid=3L;
//        Item item1=new Item();
//        Set<Item> itemList=new HashSet<>();
//        Set<ItemDTO>itemDTOSet=new HashSet<>();
//        itemDTO.setItemName("xyz");
//        itemDTO.setItemPrice(5.0f);
//        itemDTO.setItemId(itemid);
//        itemDTO.setItemQuantity(3);
//        itemDTO.setShippedDate("2023-98-01");
//        itemList.add(item);
//        order.setItemList(itemList);
//        when(orederRepository.findByOrderId(orderId)).thenReturn(order);
//        // Set<Long>ids=itemList.stream().map(x->x.getItemId()).collect(Collectors.toSet());
//        when(itemRepository.findByItemId(itemid)).thenReturn(item);
//        itemDTO.setItemId(itemid);
//        itemDTO.setItemPrice(item.getItemPrice());
//        itemDTO.setItemName(item.getItemName());
//        when(itemRepository.save(item)).thenReturn(item);
//        BeanUtils.copyProperties(itemList,itemDTOSet);
//
//        itemService.updateExistingOrderItem(itemDTO,orderId);
//
//    }
//    @Test
//    void testUpdateExistingNotinOrder() throws SQLException {
//        MockitoAnnotations.initMocks(this);
//
//        Long orderId=1L;
//        ItemDTO itemDTO=new ItemDTO();
//        Item item=new Item();
//        //  Long itemid=3L;
//        // Item item1=new Item();
//        Set<ItemDTO> itemList=new HashSet<>();
////        itemDTO.setItemName("xyz");
////        itemDTO.setItemPrice(5.0f);
////        itemDTO.setItemId(itemid);
////        itemDTO.setItemQuantity(3);
////        itemDTO.setShippedDate("2023-98-01");
////        itemList.add(itemDTO);
//        itemList=null;
//        // Mockito.when(itemRepository.save(item)).thenReturn(item);
//        // testSaveItem();
//        // Mockito.when(orderProcessingService.saveItem(itemList,orderId));
//        when(itemService.updateExistingOrderItem(itemDTO,orderId)).thenReturn(null);
//
//    }
//    @Test
//    void testDeleteOrderedItemById() throws SQLException {
//        MockitoAnnotations.initMocks(this);
//        Item item=new Item();
//        Long id;
//        id=1L;
//        List<Item> itemList=new ArrayList<>();
//        item.setItemName("xyz");
//        item.setItemPrice(5.0f);
//        item.setItemId(id);
//        item.setItemQuantity(3);
//        item.setShippedDate("2023-98-01");
//        itemList.add(item);
//        itemRepository.deleteById(id) ;
//        String s1= itemService.deleteOrderedItemById(id);
//        Assertions.assertNotNull(s1);
//
//    }
//    @Test
//    void testSaveItem() throws SQLException {
//        MockitoAnnotations.initMocks(this);
//        Order order = new Order();
//        order.setCustomerName("xyz");
//        order.setCustomerAddress("abc");
//        //  order.setOrderDate(LocalDate.parse("20230801"));
//        order.setDispatchDate("4566666");
//        order.setOrderAmount(666);
//        order.setNumberOfItems(23);
//        Long id = 1L;
//        Item item = new Item();
//        Set<Item> itemList = new HashSet<>();
//        Set<ItemDTO> itemDTOSet = new HashSet<>();
//        item.setItemName("xyz");
//        item.setItemPrice(5.0f);
//        item.setItemId(1L);
//        item.setItemQuantity(3);
//        item.setShippedDate("2023-98-01");
//        itemList.add(item);
//        BeanUtils.copyProperties(itemList, itemDTOSet);
//        when(orederRepository.findByOrderId(id)).thenReturn(order);
//        when(itemRepository.save(item)).thenReturn(item);
//        Assert.assertNotNull(itemService.saveItem(itemDTOSet, id));
    }