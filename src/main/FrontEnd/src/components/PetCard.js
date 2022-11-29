
import React, { useState, useEffect } from 'react';
import { StyledCard } from './styles/PetCard.styled';
import { Tag } from "@chakra-ui/react";
import { Button, Box, Flex, Text, Heading, Link, Spacer, List } from "@chakra-ui/react";



export default function PetCard({filter, setFilter, pet: { id, tags, petName, petStatus, imageSrc }}) {

  const [tagToFilter, setTagToFilter] = useState('');

  const filterByTag = (tag, index) => {
    setTagToFilter(tag);
    console.log(tag);    
    console.log(filter);
    setFilter(tag);
    console.log(filter);
  }

  return (
    <StyledCard>
      <div>
        <Flex flexDirection={'column'} >

          <h2>{petName}</h2>
          <Spacer />
          {tags.map((tag, index) => (
            <li key={index} >
              <Button key={index} variant="link" onClick={() => { filterByTag(tag, index)} } >
                {tag}
              </Button>
            </li>
          ))}
          <Spacer />
          <Tag alignItems="center" textAlign="justify" width={90} marginTop={4} size="md" variant="solid"
            colorScheme={petStatus == "AVAILABLE" ? "green" : (petStatus == "PENDING") ? "orange" : "red"}>{petStatus}</Tag>
        </Flex>
      </div>
      <div>
        <img src={require(`../resources/images/${imageSrc}`)} alt='' />
      </div>
    </StyledCard>
  )
}