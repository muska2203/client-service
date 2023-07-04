import { useQuery } from 'react-query';
import { clientApi } from 'services/api/apis';

export const useClientsApi = () => {
  const { data: clients, isLoading } = useQuery('GET_BY_CURRENT_USER', () => clientApi.getByCurrentUser());

  return {
    clients,
    isLoading,
  };
};
