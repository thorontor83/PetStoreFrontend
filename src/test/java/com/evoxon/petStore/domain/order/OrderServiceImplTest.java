package com.evoxon.petStore.domain.order;

import com.evoxon.petStore.domain.pet.PetServiceImpl;
import com.evoxon.petStore.domain.pet.PetStatus;
import com.evoxon.petStore.dto.OrderDto;
import com.evoxon.petStore.persistence.OrderEntity;
import com.evoxon.petStore.persistence.OrderRepository;
import com.evoxon.petStore.persistence.PetEntity;
import com.evoxon.petStore.persistence.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PetRepository petRepository;

    @Test
    void shouldCreateOrder() throws Exception {
        //given
        Order order = new Order(0L,0L,3, Date.valueOf("2023-05-04"), OrderStatus.PLACED,false);
        PetEntity petEntity = new PetEntity(0L,"German Shepperd", 1L, "Dog","Medium Friendly Smart", PetStatus.AVAILABLE);
        when(petRepository.findById(order.getPetId())).thenReturn(Optional.of(petEntity));
        when(orderRepository.save(any())).thenReturn(OrderDto.fromDomainToEntity(order));
        //when
        Order orderToCheck = orderService.createOrder(order);
        //then
        assertThat(orderToCheck.getId()).isEqualTo(order.getId());
        assertThat(orderToCheck.getPetId()).isEqualTo(order.getPetId());
        assertThat(orderToCheck.getShipDate()).isEqualTo(order.getShipDate());
        assertThat(orderToCheck.getOrderStatus()).isEqualTo(OrderStatus.PLACED);
        assertThat(orderToCheck.isComplete()).isFalse();
    }

    @Test
    void shouldNotCreateOrder() throws Exception {
        //given
        Order order = new Order(0L,0L,3, Date.valueOf("2023-05-04"), OrderStatus.PLACED,false);
        PetEntity petEntity = new PetEntity(0L,"German Shepperd", 1L, "Dog","Medium Friendly Smart", PetStatus.AVAILABLE);
        when(petRepository.findById(order.getPetId())).thenReturn(Optional.empty());
        //when
        Order orderToCheck = orderService.createOrder(order);
        //then
        assertThat(orderToCheck).isNull();
        verify(orderRepository,times(0)).save(any());

    }

    @Test
    void ShouldGetOrderById() {
        //given
        Order order = new Order(0L,0L,3, Date.valueOf("2023-05-04"), OrderStatus.PLACED,false);
        OrderEntity orderEntity = OrderDto.fromDomainToEntity(order);
        when(orderRepository.findById(order.getPetId())).thenReturn(Optional.of(orderEntity));
        //when
        Order orderToCheck = orderService.getOrderById(order.getId());
        //then
        assertThat(orderToCheck).usingRecursiveComparison().isEqualTo(order);
    }

    @Test
    void ShouldNotGetOrderById() {
        //given
        Order order = new Order(0L,0L,3, Date.valueOf("2023-05-04"), OrderStatus.PLACED,false);
        when(orderRepository.findById(order.getPetId())).thenReturn(Optional.empty());
        //when
        Order orderToCheck = orderService.getOrderById(order.getId());
        //then
        assertThat(orderToCheck).isNull();
    }

    @Test
    void shouldDeleteOrder() {
        //given
        Order order = new Order(0L,0L,3, Date.valueOf("2023-05-04"), OrderStatus.PLACED,false);
        OrderEntity orderEntity = OrderDto.fromDomainToEntity(order);
        when(orderRepository.findById(order.getPetId())).thenReturn(Optional.of(orderEntity));
        //when
        boolean isDeleted = orderService.deleteOrder(order.getId());
        //then
        assertThat(isDeleted).isTrue();
    }

    @Test
    void shouldNotDeleteOrder() {
        //given
        Order order = new Order(0L,0L,3, Date.valueOf("2023-05-04"), OrderStatus.PLACED,false);
        OrderEntity orderEntity = OrderDto.fromDomainToEntity(order);
        when(orderRepository.findById(order.getPetId())).thenReturn(Optional.empty());
        //when
        boolean isDeleted = orderService.deleteOrder(order.getId());
        //then
        assertThat(isDeleted).isFalse();
    }

    @Test
    void getInventory() {
        //given
        PetEntity petEntity1 = new PetEntity("German Shepperd", 1L, "Dog","Medium Friendly Smart", PetStatus.AVAILABLE);
        PetEntity petEntity2 = new PetEntity("Siamese", 2L, "Cat","Small Friendly", PetStatus.AVAILABLE);
        PetEntity petEntity3 = new PetEntity("Boxer", 1L, "Dog","Medium Aggressive", PetStatus.PENDING);
        PetEntity petEntity4 = new PetEntity("Pony", 3L, "Horse","Large Friendly Rideable", PetStatus.AVAILABLE);
        PetEntity petEntity5 = new PetEntity("Sphynx", 2L, "Cat","Medium Aggressive Furless", PetStatus.SOLD);
        PetEntity petEntity6 = new PetEntity("Hummingbird", 4L, "Bird","Small Delicate", PetStatus.PENDING);

        List<PetEntity> petEntityList = List.of(petEntity1,petEntity2,petEntity3,petEntity4,petEntity5,petEntity6);
        when(petRepository.findAll()).thenReturn(petEntityList);
        //when
        Map<String,Integer> availableInventory = orderService.getInventory();
        //then
        assertThat(availableInventory.keySet()).containsExactly("AVAILABLE","SOLD","PENDING");
        assertThat(availableInventory.values()).containsExactly(3,1,2);
    }
}