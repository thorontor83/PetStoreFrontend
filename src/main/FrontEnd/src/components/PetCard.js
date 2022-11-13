
import { StyledCard } from './styles/PetCard.styled';

export default function PetCard({ pet: { id, tags, petName, petStatus , imageSrc } }) {
  return (
    console.log(petName),
    <StyledCard layout={id % 2 === 0 && 'row-reverse'}>
      <div>
        <h2>{petName}</h2>
        <p>{tags}</p>
        <p>{petStatus}</p>
      </div>

      <div>
        <img src={require(`../resources/images/${imageSrc}.png`)} alt='' />
      </div>
    </StyledCard>
  )
}