package ma.credit.backend.services;

import ma.credit.backend.dtos.ClientDTO;
import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long id);
    List<ClientDTO> searchClientsByName(String nom);
    ClientDTO createClient(ClientDTO dto);
    ClientDTO updateClient(ClientDTO dto);
    void deleteClient(Long id);
}