
import { StyledCard } from './styles/PetCard.styled';
import { Tag } from "@chakra-ui/react";
import { Button, Box, Flex, Text, Heading, Link, Spacer, List } from "@chakra-ui/react";



export default function PetCard({ pet: { id, tags, petName, petStatus , imageSrc } }) {
  return (
    <StyledCard>
      <div>
        <Flex flexDirection={'column'} >

        <h2>{petName}</h2>
        <Spacer />
        {tags.map((tag,index) => (
          <li key={index}>{tag}</li>
          ))}
          <Spacer />
        <Tag alignItems="center" textAlign="justify" width={90} marginTop={4} size="md" variant="solid" 
        colorScheme={petStatus=="AVAILABLE"?"green": (petStatus=="PENDING")? "orange":"red"}>{petStatus}</Tag>
          </Flex>
      </div>
      <div>
        <img  src={require(`../resources/images/${imageSrc}.png`)} alt='' />
      </div>
    </StyledCard>
  )
}