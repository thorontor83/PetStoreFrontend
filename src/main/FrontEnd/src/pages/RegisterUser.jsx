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
import UserInput from '../components/UserInput';


export default function RegisterUser() {

  const theme = {
    colors: {
      header: '#ebfbff',
      body: '#fff',
      footer: '#003333',
    },
    mobile: '768px',
  }

  return (
    <ThemeProvider theme={theme}>
      <>
        <GlobalStyles />
        <LightHeader />
        <Container>
          <UserInput />
        </Container>
        <Footer />
      </>
    </ThemeProvider>
  );

}