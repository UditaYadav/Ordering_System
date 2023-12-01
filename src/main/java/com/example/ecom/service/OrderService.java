package com.example.ecom.service;

import com.example.ecom.common.EmptyInputException;
import com.example.ecom.common.commonException;
import com.example.ecom.entity.Items;
import com.example.ecom.entity.Orders;
import com.example.ecom.entity.StatuEnum;
import com.example.ecom.entity.Statusss;
import com.example.ecom.repo.itemRepository;
import com.example.ecom.repo.orderRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OrderService {


    @Autowired
    private orderRepositoy orderRepo;
  @Autowired
    private itemRepository itemRepo;

    @Autowired

    private com.example.ecom.repo.itemRepository itemRepository;

    public List<Orders> getAllOrders() {
        return this.orderRepo.findAll();

    }

//    public Orders addOrder(Orders orders) {
//        if (orders.getClientName().isEmpty())  {
//            throw new EmptyInputException("603", "the given order is null : ");
//        }
//
//            orderRepo.save(orders);
//            return  orders;
//
//    }
public Orders addOrder(Orders orders)
{
    orderRepo.save(orders);
    return orders;
}





//    public List<Orders> getAllOrders() {
//
//        List<Orders> orderIsEmpty= null;
//
//
//        try {
//            orderIsEmpty = orderRepo.findAll();
//
//        } catch (Exception e) {
//            throw new commonException("602", " something went wrong to get all orders : " + e.getMessage());
//        }
//
//        if (orderIsEmpty.isEmpty())
//
//            throw new commonException("601", "list completely empty we nothing to return ");
//        return orderIsEmpty;
//
//
//
//    }
//
//
//
//    public Orders getOrderById(Long id)
//    {
//        try{
//            return  orderRepo.findByOrderId(id);
//
//        }catch (IllegalArgumentException e)
//        {
//            throw  new commonException("606","given employee id is null : "+e.getMessage());
//        }catch (NullPointerException)
//        {
//            throw new commonException("607", "add id in databaase :");
//        }
//
//    }




    public Orders getOrderById(Long id) {
        return this.orderRepo.findByOrderId(id);
    }

    public Orders addOrderr(Orders orderr) {
        return orderRepo.save(orderr);

    }


//    public Orders addOrder(Orders orders) {
//        if (orders.getClientName().isEmpty())  {
//            throw new EmptyInputException("603", "the given order is null : ");
//        }
//
//        try {
//                orderRepo.save(orders);
//            return  orders;
//
//
//        }catch (IllegalArgumentException e)
//        {
//            throw  new EmptyInputException("604","something went worng in service layer : "+e.getMessage());
//        }
//    }

//    public void deleteOrder(Long id)
//    {
//        try{
//            orderRepo.deleteById(id);
//        }  catch (IllegalArgumentException e)
//        {
//            throw  new commonException("605","something went worng in service layer : "+e.getMessage());
//        }
//
//    }

    public String deleteOrder(Long id) {
        this.orderRepo.deleteById(id);
        return "deleted";
    }


    public Orders updateOrder(Orders orders, Long id) {
        Orders o = this.orderRepo.findByOrderId(id);

        if (orders != null) {
            o.setOrderId(id);
            o.setOrderAmount(orders.getOrderAmount());
            o.setOrderDate(orders.getOrderDate());
            o.setClientName(orders.getClientName());
            o.setClientAddress(orders.getClientAddress());
            o.setNumberOfItems(orders.getNumberOfItems());

        }
        return orders;
    }



    public List<Items>  getAllListOfItems() {
        return itemRepository.findAll();
    }


   public List<Items> getItemListById(Long id)
   {

       List<Items> itemList =   this.itemRepo.findAll();
       List<Items> itemobj= new ArrayList<>();
    for(Items item : itemList)
    {
        if(item.getItemId().equals(id))
        {
            itemobj.add(item);

        }
        return itemobj;

      }
        return  itemobj;

   }


   public Items updateItemOfList( Long oid, Items item) {

//        Optional<Items> odlist= Optional.ofNullable(itemRepo.findByItemId(oid));
//        Items items = null;
//        if(oid.equals(odlist.get().getItemId())){
//           items = itemRepo.save(item);
//        } else{
//            System.out.println("no such item found");
//        }
//
////        Orders order1= orderRepo.findByOrderId(oid);


       Items items = itemRepo.save(item);
       return item;

   }



//       Orders orders1 = orderRepo.findByOrderId(oid);
//        if(orders1!=null)
//        {
////           Orders ordrlistitem = getOrderById(oid);
//           Set<Items> itemList = orders1.getItemList().stream().collect(Collectors.toSet());
//            Set<Long> item2 = itemList.stream().map(e -> e.getItemId()).collect(Collectors.toSet());
//            if(itemList!=null)
//          for(Items itm : itemList) {
//
//              if (item.getItemId().equals(itm.getItemId())) {
//                  Items itemStore = itemRepo.findByItemId(itm.getItemId());
//                  if (itemStore != null) {
//                      if (item2.contains(item.getItemId())) {
//
//
//                          itemStore.setItemId(item.getItemId());
//                          itemStore.setItemName(item.getItemName());
//                          itemStore.setItemPrice(item.getItemPrice());
//                          itemStore.setItemQuantity(item.getItemQuantity());
//                          itemStore.setShippedDate(item.getShippedDate());
//                          itemStore.setId(item.getId());
//                          itemRepo.save(itemStore);
//                      }
//                      return itemStore;
//                  }
//              }
//
//              }
//
//          }



//        return item;


//   }

    public String deleteOrderWithItems(Long id) {
        itemRepository.deleteById(id);
        return "Deleted";
    }

    public Set<Items> createitem(Set<Items> item, Long id) {

        Orders order = orderRepo.findByOrderId(id);

        order.setItemList(item);
       orderRepo.save(order);
        return item;

    }


    public String showStatus(Long oid, String statusSet)
    {
        Orders orders1 = orderRepo.findByOrderId(oid);

//        if(orders1!=null)
//        {
            orders1.setStatusss(StatuEnum.valueOf(statusSet));
            orderRepo.save(orders1);
         return "update";
//        }

//     return "update";


    }

}