package com.evoxon.petStore.dto;

import com.evoxon.petStore.domain.order.Order;
import com.evoxon.petStore.domain.order.OrderOutput;
import com.evoxon.petStore.domain.pet.Pet;
import com.evoxon.petStore.persistence.OrderEntity;
import com.evoxon.petStore.persistence.PetEntity;
import com.evoxon.petStore.persistence.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderDto {

    @Autowired
    private final PetRepository petRepository;

    public OrderDto(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public static Order fromEntityToDomain(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(), orderEntity.getPetId(), orderEntity.getQuantity(),
                orderEntity.getShipDate(), orderEntity.getOrderStatus(), orderEntity.isComplete());
    }

    public static OrderEntity fromDomainToEntity(Order order) {
        return new OrderEntity(order.getId(), order.getPetId(), order.getQuantity(),
                order.getShipDate(), order.getOrderStatus(), order.isComplete());
    }

    public OrderOutput fromDomainToOutput(Order order) {
        Optional<PetEntity> optionalPetEntity = petRepository.findById(order.getPetId());
        if(optionalPetEntity.isPresent()){
            PetEntity petEntity = optionalPetEntity.get();
            Pet pet = PetDto.fromEntityToDomain(petEntity);
            return new OrderOutput(order.getId(),pet.getId(), pet.getPetName(), pet.getImageSrc(), pet.getCategory(),
                    pet.getTags(), pet.getPetStatus(), order.getQuantity(), order.getShipDate(),order.getOrderStatus(),
                    order.isComplete());
        }
        return null;
    }
}
