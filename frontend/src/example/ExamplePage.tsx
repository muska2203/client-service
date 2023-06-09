import { Link, Typography } from '@mui/material';

import { Breadcrumbs } from 'shared/components/navigation/Breadcrumbs';
import { Menu } from 'shared/components/navigation/Menu';
import { Pagination } from 'shared/components/navigation/Pagination';
import { Stepper } from 'shared/components/navigation/Stepper';
import { Tabs } from 'shared/components/navigation/Tabs';
import { TextField } from 'shared/components/inputs/TextField';

export const ExamplePage = () => {
  const menuOptions = [
    { title: 'Profile', handler: () => console.log('Profile clicked!') },
    { title: 'My account', handler: () => console.log('My account clicked!') },
    { title: 'Logout', handler: () => console.log('Logout clicked!') },
  ];

  const steps = [
    { label: 'First step', key: 'first' },
    { label: 'Second step', key: 'second' },
    { label: 'Third step', key: 'third' },
  ];
  
  const tabItems = [
    { label: 'First tab', key: 'first', Component: <>Hello world 1</> },
    { label: 'Second tab', key: 'second', Component: <>Hello world 2</> },
    { label: 'third tab', key: 'third', Component: <>Hello world 3</> },
  ];

  return (
    <>
      <h1>Пример использования разных компонентов</h1>
      <Breadcrumbs separator='›'>
        <Link underline='hover' color='inherit' href='/'>
          Just
        </Link>
        <Link underline='hover' color='inherit' href='/'>
          Simple
        </Link>
        <Typography color='text.primary'>Example</Typography>
      </Breadcrumbs>

      <Menu items={menuOptions} title={'Menu example'} color={'warning'} variant={'contained'} />

      <Pagination count={10} />

      <Stepper steps={steps} activeStep={1} alternativeLabel />

      <Tabs items={tabItems} defaultSelected={2}/>

      <TextField label="Outlined Text Field" variant="standard" required helperText={'Надо заполнить'}/>
      </>
  );
};
