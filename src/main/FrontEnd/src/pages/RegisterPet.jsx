import React from 'react';
import LightHeader from "../components/LightHeader";
import Footer from "../components/Footer";
import { ThemeProvider } from "styled-components";
import GlobalStyles from "../components/styles/Global";
import { Container } from "../components/styles/Container.styled";
import PetInput from "../components/PetInput";


export default function RegisterPet() {

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
          <PetInput />
        </Container>
        <Footer />
      </>
    </ThemeProvider>
  );

}