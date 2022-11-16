import React, { useState, useEffect } from 'react';
import LightHeader from "../components/LightHeader";
import Footer from "../components/Footer";
import axios from "axios";
import OrderCard from '../components/OrderCard';
import { ThemeProvider } from "styled-components";
import GlobalStyles from "../components/styles/Global";
import { Container } from "../components/styles/Container.styled";

const urlOrderCatalog = "http://localhost:8080/api/v1/store/order";

export default function Orders() {

  const theme = {
    colors: {
      header: '#ebfbff',
      body: '#fff',
      footer: '#003333',
    },
    mobile: '768px',
  }

  const [orders, setOrders] = useState([]);
  const [response, setResponse] = useState("hola");



  const fetchOrders = () => {

    axios.get(urlOrderCatalog).then(res => {
        setResponse(res);
        if(response!="hola"){setOrders(response.data)};
  })
}

    
    

  useEffect(() => {
    fetchOrders();
  }, [])


  return (
    <ThemeProvider theme={theme}>
      <>
        <GlobalStyles />
        <LightHeader />
        <Container>
        {/*orders.map((order, index) => (
            <OrderCard key={index} order={order} />
        ))*/}
        {/*<OrderCard  order={orders[0]} />*/}
        {(response==="hola")?"adios":response.data[0]}
        </Container>
        <Footer />
      </>
    </ThemeProvider>
  );

}