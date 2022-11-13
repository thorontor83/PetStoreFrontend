import React, { useState, useEffect } from 'react';
import { Button, Image, Box, Flex, Text, Heading, Link, Spacer, List } from "@chakra-ui/react";
import axios from "axios";
import LightHeader from "../components/LightHeader";
import Footer from "../components/Footer";
import { ThemeProvider } from "styled-components";
import GlobalStyles from "../components/styles/Global";
import PetCard from '../components/PetCard';
import { Container } from "../components/styles/Container.styled";


const urlPetCatalog = "http://localhost:8080/api/v1/petpaged?page=";



export function PetCatalog() {

  const theme = {
    colors: {
      header: '#ebfbff',
      body: '#fff',
      footer: '#003333',
    },
    mobile: '768px',
  }

  const [pets, setPets] = useState([]);
  const [pet, setPet] = useState();
  const [maxPage, setMaxPage] = useState();
  const [page, setPage] = useState(0);


  const fetchPetCatalog = () => {

    axios.get(urlPetCatalog + page).then(res => {
      console.log(res)
      const data = res.data
      setPets(data.content);
      setMaxPage(data.totalPages)
      console.log(pets);
      console.log(maxPage);
    })
  }

  useEffect(() => {
    fetchPetCatalog();
  }, [page])

  const onChangePage = (next) => {
    if (page + next < 0) return;
    if (page + next > maxPage - 1) return;
    setPage(page + next);
  }


  return (
    <ThemeProvider theme={theme}>

      <>
        <GlobalStyles />
        <LightHeader />
        <Heading align="center" as="h1" size="lg" m={4}>
          List of Pets
        </Heading>
        <Flex>
          <Box bg="blue.100" p={1} borderRadius="lg">
            <Flex>
              <Button onClick={() => onChangePage(-1)}>Prev</Button>
              <Text fontSize="lg" p={2}>
                {page + 1}
              </Text>
              <Button onClick={() => onChangePage(+1)}>Next</Button >
            </Flex>
          </Box>
          <Spacer />
          <Link href='http://localhost:3000/courses/register'>
            <Button colorScheme="purple">
              Add a new Course
            </Button>
          </Link>
          <Spacer />
          <Link href='http://localhost:3000/courses/admin'>
            <Button colorScheme="orange">
              Administrate Courses
            </Button>
          </Link>
          <Spacer />
          <Spacer />
          <Link href='http://localhost:3000/'>
            <Button colorScheme="red">
              Back to Main Page
            </Button>
          </Link>
          <Spacer />
        </Flex>
        <Container>
        {pets.map((pet,index) => (
          <PetCard key={index} pet={pet} />
        ))}
        </Container>
        <Footer />
      </>
    </ThemeProvider>

  


  );

}