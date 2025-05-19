package ma.credit.backend.services;

import ma.credit.backend.dtos.ClientDTO;
import ma.credit.backend.entities.Client;
import ma.credit.backend.mappers.ClientMapper;
import ma.credit.backend.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepo;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepo, ClientMapper clientMapper) {
        this.clientRepo = clientRepo;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepo.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepo.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public List<ClientDTO> searchClientsByName(String nom) {
        return clientRepo.findAll().stream()
                .filter(c -> c.getNom().toLowerCase().contains(nom.toLowerCase()))
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO createClient(ClientDTO dto) {
        Client entity = clientMapper.toEntity(dto);
        Client saved = clientRepo.save(entity);
        return clientMapper.toDto(saved);
    }

    @Override
    public ClientDTO updateClient(ClientDTO dto) {
        Client existing = clientRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        existing.setNom(dto.getNom());
        existing.setEmail(dto.getEmail());
        Client updated = clientRepo.save(existing);
        return clientMapper.toDto(updated);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }
}