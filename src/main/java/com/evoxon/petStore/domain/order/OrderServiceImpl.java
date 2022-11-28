package com.evoxon.petStore.domain.order;

import com.evoxon.petStore.domain.pet.PetStatus;
import com.evoxon.petStore.dto.OrderDto;
import com.evoxon.petStore.persistence.OrderEntity;
import com.evoxon.petStore.persistence.OrderRepository;
import com.evoxon.petStore.persistence.PetEntity;
import com.evoxon.petStore.persistence.PetRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    final private OrderRepository orderRepository;
    final private PetRepository petRepository;
    final private OrderDto orderDto;

    public OrderServiceImpl(OrderRepository orderRepository, PetRepository petRepository, OrderDto orderDto) {
        this.orderRepository = orderRepository;
        this.petRepository = petRepository;
        this.orderDto = orderDto;
    }

    public Order createOrder(Order order) throws Exception {
        Optional<PetEntity> optionalPetEntity = petRepository.findById(order.getPetId());
        if (optionalPetEntity.isPresent() && optionalPetEntity.get().getPetStatus().equals(PetStatus.AVAILABLE)) {
            order.approve();
            return OrderDto.fromEntityToDomain(orderRepository.save(OrderDto.fromDomainToEntity(order)));
        } else {
            return null;
        }
    }

    public Order getOrderById(Long orderId) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderId);
        return optionalOrderEntity.map(OrderDto::fromEntityToDomain).orElse(null);
    }

    public Order getOrderOutputById(Long orderId) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderId);
        return optionalOrderEntity.map(OrderDto::fromEntityToDomain).orElse(null);
    }



    public Boolean deleteOrder(Long orderIdToDelete) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderIdToDelete);
        if(optionalOrderEntity.isPresent()){
            orderRepository.delete(optionalOrderEntity.get());
            return true;
        }
        else{
            return false;
        }
    }

    public Map<String, Integer> getInventory() {
        Map<String, Integer> inventory = new HashMap<String, Integer>();
        List<PetEntity> petEntityList = petRepository.findAll();
        for (PetStatus petStatus : PetStatus.values()){
            inventory.put(petStatus.name(),(int)petEntityList.stream().filter(p->p.getPetStatus()==petStatus).count());
        }
        return inventory;
    }

    public List<Order> getAllOrders() {
        List<OrderEntity> entityList = orderRepository.findAll();
        return entityList.stream().map(OrderDto::fromEntityToDomain).collect(Collectors.toList());
    }

    public List<OrderOutput> getAllOrderOutputs() {
        List<OrderEntity> entityList = orderRepository.findAll();
        return entityList.stream().map(OrderDto::fromEntityToDomain).map(orderDto::fromDomainToOutput).collect(Collectors.toList());
    }


}

