package com.evoxon.petStore.domain.order;

import java.util.Map;

public interface OrderService {

    public Order createOrder(Order order);

    public Order getOrderById(Long orderId);

    public Boolean deleteOrder(Long orderIdToDelete);

    public Map<String, Integer> getInventory();




}
