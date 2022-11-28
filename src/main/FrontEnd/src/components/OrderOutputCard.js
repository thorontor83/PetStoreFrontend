
import React, { useState, useEffect } from 'react';
import { StyledCard } from './styles/OrderCard.styled';
import { Tag } from "@chakra-ui/react";
import axios from "axios";
import { Button, Box, Flex, Text, Heading, Link, Spacer, List } from "@chakra-ui/react";

const urlPetCatalog = "http://localhost:8080/api/v1/pet/";


export default function OrderOutputCard({ order: { id, petId, petName, imageSrc, category, tags, petStatus, quantity, shipDate, orderStatus, complete } }) {

    const [pet, setPet] = useState(null);
    



    return (
        
            <StyledCard>
                <div>
                    <Flex width={400} flexDirection={'column'} >
                        <h2>{petName}</h2>
                        <Flex flexDirection={'row'}>
                            <p >Quantity:&nbsp;</p>
                            <div>{quantity}</div>
                        </Flex>
                        <Flex flexDirection={'row'}>
                            <p>Delivery date:&nbsp;</p>
                            <div>{shipDate.slice(0, 10)}</div>
                        </Flex>
                        <Tag alignItems="center" textAlign="justify" width={90} marginTop={4} size="md" variant="solid"
                            colorScheme={orderStatus == "PLACED" ? "orange" : (orderStatus == "APPROVED") ? "green" : "purple"}>{orderStatus}</Tag>
                    </Flex>
                </div>
                <div>
                    {(imageSrc !== undefined) ?
                        <img src={require(`../resources/images/${imageSrc}.png`)} alt='' /> : "Image missing"}
                </div>
                <Spacer />
                <div>
                    {(complete == true) ?
                        <img  src={require(`../resources/images/completed-stamp.png`)} alt='' /> : ""}
                </div>
            </StyledCard>
    )
}