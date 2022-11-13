import { StyledHeader, Nav, Logo, Image } from "./styles/Header.styled";
import { Container } from "./styles/Container.styled";
import { Flex } from "./styles/Flex.styled";
import { Button } from "./styles/Button.styled";

export default function LightHeader() {
    return (
        <StyledHeader>
            <Container>
                <Nav>
                    <Logo src = {require ('../resources/images/logo.png')} alt='' />
                    <Button>Register now!</Button>
                </Nav>
            </Container>
        </StyledHeader>
    )
}