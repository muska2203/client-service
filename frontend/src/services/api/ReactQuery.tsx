import { FC, ReactNode } from 'react';
import { QueryClient, QueryClientProvider, QueryClientConfig } from 'react-query';

const onError = (e: any) => {
  console.log('Error', e);
};

const config: QueryClientConfig = {
  defaultOptions: {
    queries: {
      onError,
      refetchOnWindowFocus: false,
    },
    mutations: {
      onError,
    },
  },
};

const queryClient = new QueryClient(config);

interface QueryProviderProps {
  children: ReactNode;
}

export const QueryProvider: FC<QueryProviderProps> = ({ children }) => (
  <QueryClientProvider client={queryClient}>{children}</QueryClientProvider>
);
