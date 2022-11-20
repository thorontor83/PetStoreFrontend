
import styled from 'styled-components'

export const StyledCard = styled.div`
  display: flex;
  align-items: center;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.25);
  margin: 40px 0;
  padding: 60px;
  flex-direction: 'row';
  h2{
  font-weight: bold;
  color: #00000;
  font-size: 32px;
  margin-bottom: 4px;
  }
  li{
    margin-bottom:4px;
  }
  Tag{
    align-items: center;
    text-align: center;
  }
  img {

    height: 150px;
    
  }
  & > div {
    flex: 1;
  }
  @media (max-width: ${({ theme }) => theme.mobile}) {
    flex-direction: column;
  }
`