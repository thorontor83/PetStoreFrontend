
import React, { useState, useEffect } from 'react';
import { StyledCard } from './styles/PetCard.styled';
import { Tag } from "@chakra-ui/react";
import axios from "axios";
import { Button, Box, Flex, Text, Heading, Link, Spacer, List } from "@chakra-ui/react";

const urlPetCatalog = "http://localhost:8080/api/v1/pet/1";


export default function OrderCard({ order: { id, petId, quantity, shipDate, orderStatus, complete } }) {

    const [pet, setPet] = useState();

    console.log("petId");


    {/*const fetchPet = () => {

        console.log(petId);

        axios.get(urlPetCatalog).then(res => {
            console.log(res)
            const data = res.data
            setPet(data);
        })
    }

    useEffect(() => {
        fetchPet();
    }, [])*/}


    return (
        {/*<StyledCard>
            <div>
                <Flex flexDirection={'column'} >
                    <h2>{pet.petName}</h2>
                    <Spacer />
                    <Tag alignItems="center" textAlign="justify" width={90} marginTop={4} size="md" variant="solid"
                        colorScheme={orderStatus == "PLACED" ? "orange" : (orderStatus == "APPROVED") ? "green" : "purple"}>{orderStatus}</Tag>
                </Flex>
            </div>
            <div>
                <img src={require(`../resources/images/${pet.imageSrc}.png`)} alt='' />
            </div>
    </StyledCard>*/}


    )
}