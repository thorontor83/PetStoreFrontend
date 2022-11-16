import { StyledHeader, Nav, Logo, Image } from "./styles/Header.styled";
import { Container } from "./styles/Container.styled";
import { Flex } from "./styles/Flex.styled";
import { Button } from "./styles/Button.styled";
import { Link } from "@chakra-ui/react";
import { Spacer } from '@chakra-ui/react';


export default function Header() {
    return (
        <StyledHeader>
            <Container>
                <Nav>
                    <Logo src={require('../resources/images/logo.png')} alt='' />
                    <Link href='http://localhost:3000/user'>
                        <Button>Register now!</Button>
                    </Link>
                </Nav>
                <Flex>
                    <div>
                        <h1>"Neque porro quisquam est qui dolorem dolor sit amet, consectetur, adipisci velit..."</h1>

                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed consequat ullamcorper posuere.
                            Ut blandit massa felis, non maximus orci condimentum quis. Suspendisse a euismod urna. Maecenas
                            fringilla vulputate elit malesuada maximus. Integer sed justo at lacus mollis ornare posuere
                            eu turpis. Nam at porttitor felis. Duis et ligula eget neque dapibus fermentum et ut nisi.
                            Ut in facilisis ex. Suspendisse non velit ultrices, egestas augue vitae, congue nunc.
                            Curabitur vitae nisl urna.
                        </p>
                        <Link href='http://localhost:3000/pets'>
                            <Button bg="#ff0099" color="#fff" type="link">
                                Visit our Catalog
                            </Button>
                        </Link>
                    </div>
                    <flex flex-direction="column" align = "center">
                        <Image src={require("../resources/images/headerimage.png")} alt='' />
                        <span />
                        <Link href='http://localhost:3000/orders'>
                            <Button margintop={"110px"} bg="darkcyan" color="#fff" type="link">
                                Check our Delivery Orders
                            </Button>
                        </Link>
                    </flex>
                </Flex>
            </Container>
        </StyledHeader>
    )
}