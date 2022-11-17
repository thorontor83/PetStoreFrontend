
import React, { useState, useEffect } from 'react';
import { StyledCard } from './styles/PetCard.styled';
import { Tag } from "@chakra-ui/react";
import axios from "axios";
import { Button, Box, Flex, Text, Heading, Link, Spacer, List } from "@chakra-ui/react";

const urlPetCatalog = "http://localhost:8080/api/v1/pet/";


export default function OrderCard({ order: { id, petId, quantity, shipDate, orderStatus, complete } }) {

    const [pet, setPet] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    var data;

        useEffect (() => {
            if(petId!=undefined){
            axios.get(urlPetCatalog+petId).then(res => {
                data = res.data;
                setPet(data);
                setIsLoading(false);
            })}
        },[])



    return (
        (isLoading==false)?(           
        <StyledCard>
            <div>
                <Flex flexDirection={'column'} >
                    <h2>{pet.petName}</h2>
                    <Spacer />
                    <Tag alignItems="center" textAlign="justify" width={90} marginTop={4} size="md" variant="solid"
                        colorScheme={orderStatus == "PLACED" ? "orange" : (orderStatus == "APPROVED") ? "green" : "purple"}>{orderStatus}</Tag>
                </Flex>
            </div>
            <div>
                {(pet.imageSrc!==undefined)?
                <img src={require(`../resources/images/${pet.imageSrc}.png`)} alt='' />:"falta imagen"}
            </div>
        </StyledCard>
        ):"loading"

    )
}