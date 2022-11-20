import { StyledHeader, Nav, Logo, Image } from "./styles/Header.styled";
import { Container } from "./styles/Container.styled";
import { Button } from "./styles/Button.styled";
import { Link } from "@chakra-ui/react";


export default function OrderHeader() {
    return (
        <StyledHeader>
            <Container>
                <Nav>
                    <Link href='http://localhost:3000'>
                    <Logo src = {require ('../resources/images/logo.png')} alt='' />
                    </Link>
                    <Link href='http://localhost:3000/orders/register'>
                    <Button bg="orange">Place a new Order!</Button>
                    </Link>
                </Nav>
            </Container>
        </StyledHeader>
    )
}