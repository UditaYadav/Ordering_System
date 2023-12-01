package com.example.ecom.controller;

import com.example.ecom.common.commonException;
import com.example.ecom.entity.Items;
import com.example.ecom.entity.Orders;
import com.example.ecom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class EcommerenceController {

    @Autowired
    private OrderService orderServiceo;
    @GetMapping("hey")
    String getString() {
        return "hey";
    }


    @GetMapping("/api/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = orderServiceo.getAllOrders();


        if (orders.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(orders));
    }




    @GetMapping("api/orders/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") Long id) {
        Orders orders = orderServiceo.getOrderById(id);

//        if (orders != null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
        return new   ResponseEntity<>(orders, HttpStatus.OK);

    }






//    @PostMapping("/api/orders")
//    public ResponseEntity<?> addOrderr(@RequestBody Orders orderr) {



//        Orders orders1 = orderServiceo.addOrderr(orderr);
//        return new ResponseEntity<>(orders1, HttpStatus.OK);
//        try {
//            Orders orders = orderServiceo.addOrder(orderr);
//            return new ResponseEntity<>(orders, HttpStatus.OK);
//        }catch (commonException e)
//        {
//            commonException common = new commonException(e.getErrorCode(),e.getErrorMEssage());
//            return new ResponseEntity<commonException>(common, HttpStatus.BAD_REQUEST);
//        }catch (Exception e)
//        {
//            commonException common = new commonException("607","something went worng :");
//            return new ResponseEntity<commonException>(common, HttpStatus.BAD_REQUEST);
//
//        }
//}



    @PostMapping("/api/orders")
    public Orders addOrderr(@RequestBody Orders orderr)
    {
        Orders orders1 = orderServiceo.addOrder(orderr);
        return orders1;
    }




//    @PostMapping("/api/orders")
//    public ResponseEntity<Orders> addOrderr(@RequestBody Orders orderr)
//        Orders orders1 = orderServiceo.addOrder(orderr);
//        return new ResponseEntity<>(orders1, HttpStatus.OK);
//    }

    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<String> deleteOrderByID(@PathVariable("id") Long id) {

        orderServiceo.deleteOrder(id);

        return new ResponseEntity<>("deleted", HttpStatus.OK);


    }


    @PutMapping("/api/orders/{id}")
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders orders, @PathVariable("id") Long id)
    {
        orderServiceo.updateOrder(orders, id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/api/items")
    public List<Items> getItemList() {
        return this.orderServiceo.getAllListOfItems();
    }

    @GetMapping("/api/orders-items/{id}")
    public List<Items> getListOfItemByIddd(@PathVariable("id") Long id) {
        List<Items> list = orderServiceo.getItemListById(id);
        return list;
    }


    @DeleteMapping("api/orders-items/{id}")
    public String deleteItemById(@PathVariable("id") Long id) {
        orderServiceo.deleteOrderWithItems(id);

        return "Item is Deleted";
    }

    @PostMapping(value = "/api/orders-items/{id}")
    public Set<Items> createlistofitem(@RequestBody Set<Items> itemss, @PathVariable("id") Long id) {
        Set<Items> save = this.orderServiceo.createitem(itemss, id);
        return save;
    }

//    @PutMapping("/api/order-items/{orderid}")
//    public Items updateilistofItem(@RequestBody Items item, @PathVariable("id") Long id) {
//        Items items = orderServiceo.updateItemOfList(item, id);
//        return items;
//    }
@PutMapping("/api/order-items/{orderid}")
    public Items updateilistofItem(@PathVariable Long orderid, @RequestBody Items item1) {
        item1.setItemId(orderid);
        return orderServiceo.updateItemOfList(orderid,item1);
    }


    @PutMapping("/api/orders/{oid}/{ustatus}")
    public String updateStatus(@PathVariable Long oid, @PathVariable String ustatus) {

        orderServiceo.showStatus(oid, ustatus);
        return "updated";

    }
}