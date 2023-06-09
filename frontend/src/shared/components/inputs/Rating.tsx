import { Rating as MuiRating } from '@mui/material';
import { RatingProps as MuiRatingProps } from '@mui/material/Rating/Rating';

type RatingProps = MuiRatingProps;

export const Rating = ({ ...rest }: RatingProps) => {
  return <MuiRating {...rest} />;
};
