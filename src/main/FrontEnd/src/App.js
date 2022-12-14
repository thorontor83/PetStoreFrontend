import { useForm } from "react-hook-form";
import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import UploadPage from "./pages/Upload";
import { FormPage } from "./pages/FormPage";
import { PetCatalog } from "./pages/PetCatalog";
import RegisterUser from "./pages/RegisterUser";
import RegisterPet from "./pages/RegisterPet";
import { ThemeProvider } from "styled-components";
import GlobalStyles from "./components/styles/Global";
import Orders from "./pages/Orders";
import  OrdersForm  from "./pages/OrdersForm";
import OrderOutput from "./pages/OrderOutput";


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
  <Route path="/pets/register" element={<RegisterPet/>} />
  <Route path="/user" element={<RegisterUser/>} />
  <Route path="/orders" element={<OrderOutput/>} />
  <Route path="/orders/register" element={<OrdersForm/>} />
  </Routes>
  </>
  </ThemeProvider>

  );
}

export default App;
