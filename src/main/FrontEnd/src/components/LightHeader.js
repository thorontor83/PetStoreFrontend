import { StyledHeader, Nav, Logo, Image } from "./styles/Header.styled";
import { Container } from "./styles/Container.styled";
import { Button } from "./styles/Button.styled";
import { Link } from "@chakra-ui/react";


export default function LightHeader() {
    return (
        <StyledHeader>
            <Container>
                <Nav>
                    <Logo src = {require ('../resources/images/logo.png')} alt='' />
                    <Link href='http://localhost:3000/user'>
                    <Button>Register now!</Button>
                    </Link>
                </Nav>
            </Container>
        </StyledHeader>
    )
}