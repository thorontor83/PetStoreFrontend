import React, { useState, useEffect } from 'react';
import { Box, Flex, Text } from "@chakra-ui/react";
import { Button } from "./styles/Button.styled";



export default function PageButton({page,setPage,maxPage,setMaxPage}) {


    const onChangePage = (next) => {
        if (page + next < 0) return;
        if (page + next > maxPage - 1) return;
        setPage(page + next);
        setMaxPage(maxPage);
      }

    return (

        <Box  m={2} bg="blue.100" p={1} borderRadius="lg">
            <Flex>
              <Button onClick={() => onChangePage(-1)}>Prev</Button>              
                <Text fontSize="xl" p={3} >
                  {page+1}/{maxPage}  
                </Text>           
              <Button onClick={() => onChangePage(+1)}>Next</Button >
            </Flex>
        </Box>

    )
}