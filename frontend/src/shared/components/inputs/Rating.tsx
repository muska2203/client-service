import { Rating as MuiRating } from '@mui/material';
import { RatingProps as MuiRatingProps } from '@mui/material/Rating/Rating';

import { FormItem } from "shared/components/form/FormItem";

type RatingProps = MuiRatingProps & {
  name: string;
};

export const Rating = ({ name, ...rest }: RatingProps) => {
  return (
    <FormItem name={name}>
      {({field: {value, onChange}}) => (
        <MuiRating name={name} value={value as number} onChange={onChange} {...rest} />
      )}
    </FormItem>
  );
};
