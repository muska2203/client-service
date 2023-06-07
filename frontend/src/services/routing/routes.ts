import { RouteInfo } from 'shared/types/RouteInfo';
import { HomePage } from 'home/HomePage';
import { OrderPage } from 'order/OrderPage';
import { ExamplePage } from 'example/ExamplePage';

const authRequiredRoutes: RouteInfo[] = [
  { name: 'home', title: 'Home', route: '/', Component: HomePage },
  { name: 'order', title: 'Order', route: '/order', Component: OrderPage },
];

const authOptionalRoutes: RouteInfo[] = [
  { name: 'example', title: 'Example', route: '/example', Component: ExamplePage },
];

export const appRoutes = [...authRequiredRoutes, ...authOptionalRoutes];
