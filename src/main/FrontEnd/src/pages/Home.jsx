import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import { Container } from "../components/styles/Container.styled";
import Header from "../components/Header";
import Footer from "../components/Footer";
import Card from "../components/Card";
import { ThemeProvider } from "styled-components";
import GlobalStyles from "../components/styles/Global";
import content from '../content';

export default function Home() {

  const [imagePath, setImagePath] = useState();

  const theme = {
    colors: {
      header: '#ebfbff',
      body: '#fff',
      footer: '#003333',
    },
    mobile: '768px',
  }


  return (
    <ThemeProvider theme = {theme}>
      <>
      <GlobalStyles />
        <Header />
        <Container>
          {content.map((item, index) => (
          <Card key={index} item={item} />
          ))}
        </Container>
        <Footer />
      </>
    </ThemeProvider>
  );
}
