import React, { useState, useEffect } from 'react';
import { Image, Box, Flex, Text, Heading, Link, Spacer, List } from "@chakra-ui/react";
import { Button } from "../components/styles/Button.styled";
import axios from "axios";
import LightHeader from "../components/LightHeader";
import Footer from "../components/Footer";
import { ThemeProvider } from "styled-components";
import GlobalStyles from "../components/styles/Global";
import PetCard from '../components/PetCard';
import { Container } from "../components/styles/Container.styled";
import PageButton from "../components/PageButton";


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
    })
  }

  const onChangePage = (next) => {
    if (page + next < 0) return;
    if (page + next > maxPage - 1) return;
    setPage(page + next);
  }

  useEffect(() => {
    fetchPetCatalog();
  }, [page])

  return (
    <ThemeProvider theme={theme}>
      <>
        <GlobalStyles />
        <LightHeader />
        <Container >
          <Flex>                       
            <PageButton
              page={page} setPage={setPage}
              maxPage={maxPage} setMaxPage={setMaxPage}
            />
            <Spacer />
            <Link href='/pets/register'>
            <Button color='black' bg="lightBlue" margintop={"10px"} >Add a new Pet</Button>
            </Link>
          </Flex>
          {pets.map((pet, index) => (
            <PetCard key={index} pet={pet} />
          ))}
          <Flex>
            <Spacer />
            <PageButton
              page={page} setPage={setPage}
              maxPage={maxPage} setMaxPage={setMaxPage}
            />
            <Spacer />
          </Flex>
        </Container>
        <Footer />
      </>
    </ThemeProvider>
  );
}