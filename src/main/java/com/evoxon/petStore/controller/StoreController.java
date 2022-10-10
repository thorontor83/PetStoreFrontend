package com.evoxon.petStore.controller;

import com.evoxon.petStore.domain.order.Order;
import com.evoxon.petStore.domain.order.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class StoreController {

    final private OrderServiceImpl orderServiceImpl;

    public StoreController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @GetMapping(path = "api/v1/store/order/{orderIdString}")
    public ResponseEntity<Object> getOrderById(@PathVariable String orderIdString){
        if (orderIdString == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order's Id is not valid");
        }
        Long orderId = Long.parseLong(orderIdString);
        Order orderToGet = orderServiceImpl.getOrderById(orderId);
        if (orderToGet != null){
            return ResponseEntity.status(HttpStatus.OK).body(orderToGet);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }

    @GetMapping(path = "api/v1/store/inventory")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getInventory(){
        Map<String, Integer> inventory = new HashMap<String, Integer>();
        inventory = orderServiceImpl.getInventory();
        if(inventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(inventory);
        }
    }


    @PostMapping(path = "api/v1/store/order")
    public ResponseEntity<Object> createOrder(@RequestBody Order order) throws Exception {
        if (order == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order is not valid");
        }
        Order orderCreated = orderServiceImpl.createOrder(order);
        if (orderCreated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Required pet is not Available");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(orderCreated);
        }
    }

    @DeleteMapping(path = "api/v1/store/order/{orderIdString}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteOrder(@PathVariable String orderIdString){
        if (orderIdString == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order's Id is not valid");
        }
        Long orderIdToDelete = Long.parseLong(orderIdString);
        Boolean deleteIsValid = orderServiceImpl.deleteOrder(orderIdToDelete);
        if (deleteIsValid) {
            return ResponseEntity.status(HttpStatus.OK).body("Order deleted correctly");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order to delete not found");
        }
    }

}
