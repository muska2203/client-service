import { Link, Typography } from '@mui/material';

import { Breadcrumbs } from 'shared/components/navigation/Breadcrumbs';
import { Menu } from 'shared/components/navigation/Menu';
import { Pagination } from 'shared/components/navigation/Pagination';
import { Stepper } from 'shared/components/navigation/Stepper';
import { Tabs } from 'shared/components/navigation/Tabs';
import { Progress } from 'shared/components/feedback/Progress';
import { Skeleton } from 'shared/components/feedback/Skeleton';
import { Chip } from 'shared/components/dataDisplay/Chip';
import { Divider } from 'shared/components/dataDisplay/Divider';

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

      <Tabs items={tabItems} defaultSelected={2} />

      <Progress type={'circular'} value={65} variant={'determinate'} />
      <Progress type={'circular'} />

      <Skeleton variant='rounded' width={210} height={60} />

      <Chip label='Clickable' variant='outlined' onClick={console.log} />
      <Chip label='Deletable' onDelete={console.log} />

      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla quis euismod dui, id malesuada neque. Etiam
      tincidunt fermentum pharetra. Aliquam erat volutpat. Nam nec magna purus.
      <Divider textAlign='left'>LEFT</Divider>
      In lacinia eros sit amet interdum gravida. Sed libero ligula, eleifend at ullamcorper id, laoreet eu mi. Morbi
      sollicitudin, ipsum lacinia maximus imperdiet, augue tortor pellentesque purus, a vehicula velit nunc vitae neque.
      Suspendisse porta ac lectus in placerat.

    </>
  );
};
