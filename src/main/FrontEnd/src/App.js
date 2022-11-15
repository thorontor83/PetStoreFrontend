import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import UploadPage from "./pages/Upload";
import { FormPage } from "./pages/FormPage";
import { PetCatalog } from "./pages/PetCatalog";
import RegisterUser from "./pages/RegisterUser";
import { ThemeProvider } from "styled-components";
import GlobalStyles from "./components/styles/Global";


function App() {


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
  <Routes> 
  <Route path="/" element={<Home />} />
  <Route path="/pets" element={<PetCatalog/>} />
  <Route path="/upload" element={<UploadPage />} />
  <Route path="/form" element={<FormPage/>} />
  <Route path="/user" element={<RegisterUser/>} />
  </Routes>
  </>
  </ThemeProvider>

  );
}

export default App;
