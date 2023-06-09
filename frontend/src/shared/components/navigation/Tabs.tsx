import { Box, Tab, Tabs as MuiTabs } from '@mui/material';
import { TabsProps as MuiTabsProps } from '@mui/material/Tabs/Tabs';
import { ReactNode, useState } from 'react';

type TabsProps = MuiTabsProps & {
  items: Array<{ label: string; Component: ReactNode }>;
  defaultSelected?: number;
};

export const Tabs = ({ items, defaultSelected = 0, ...rest }: TabsProps) => {
  const [selected, setSelected] = useState(defaultSelected);

  return (
    <>
      <MuiTabs {...rest} value={selected} onChange={(_, value) => setSelected(value)}>
        {items.map(({ label }, i) => (
          <Tab label={label} value={i} key={i}/>
        ))}
      </MuiTabs>
      <Box sx={{ p: 3 }}>{items[selected].Component}</Box>
    </>
  );
};
