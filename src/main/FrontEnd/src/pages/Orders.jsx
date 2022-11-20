import React, { useState, useEffect } from 'react';
import OrderHeader from '../components/OrderHeader';
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

  const [orders, setOrders] = useState([null]);
  const [isLoading, setIsLoading] = useState(true);
  var data = [];



  useEffect(() => {
    axios.get(urlOrderCatalog).then(res => {
      console.log(res);
      data = res.data;
      setOrders(data);
      setIsLoading(false);
    })
  }, [])




  return (
    <ThemeProvider theme={theme}>
      <>
        <GlobalStyles />
        <OrderHeader />
        <Container>
          {(isLoading == false) ? (
          orders.map((order, index) => (
            <OrderCard key={index} order={order} />))
          ) : "loading"}
        </Container>
        <Footer />
      </>
    </ThemeProvider>
  );
}