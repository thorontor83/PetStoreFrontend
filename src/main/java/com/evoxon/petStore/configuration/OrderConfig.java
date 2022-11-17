package com.evoxon.petStore.configuration;

import com.evoxon.petStore.domain.order.OrderStatus;
import com.evoxon.petStore.domain.pet.PetStatus;
import com.evoxon.petStore.persistence.OrderEntity;
import com.evoxon.petStore.persistence.OrderRepository;
import com.evoxon.petStore.persistence.PetEntity;
import com.evoxon.petStore.persistence.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.List;

@Configuration
public class OrderConfig {

    final private OrderRepository orderRepository;

    public OrderConfig(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner3(PetRepository petRepository){
        return args -> {
            OrderEntity orderEntity1 = new OrderEntity(0L,2, Date.valueOf("2023-11-24"), OrderStatus.DELIVERED,true);
            OrderEntity orderEntity2 = new OrderEntity(1L,3, Date.valueOf("2023-05-04"), OrderStatus.PLACED,false);
            OrderEntity orderEntity3 = new OrderEntity(2L,2, Date.valueOf("2023-08-20"), OrderStatus.APPROVED,false);
            OrderEntity orderEntity4 = new OrderEntity(3L,1, Date.valueOf("2023-05-12"), OrderStatus.APPROVED,false);
            OrderEntity orderEntity5 = new OrderEntity(4L,4, Date.valueOf("2023-07-11"), OrderStatus.APPROVED,false);
            OrderEntity orderEntity6 = new OrderEntity(5L,3, Date.valueOf("2023-05-04"), OrderStatus.DELIVERED,false);



            orderRepository.saveAllAndFlush(List.of(orderEntity1,orderEntity2,orderEntity3,orderEntity4,orderEntity5,orderEntity6));
        };
    }
}
