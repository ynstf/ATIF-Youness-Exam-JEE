package ma.credit.backend.services;

import ma.credit.backend.dtos.UtilisateurDTO;
import ma.credit.backend.entities.Utilisateur;
import ma.credit.backend.mappers.UtilisateurMapper;
import ma.credit.backend.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository repo;
    private final UtilisateurMapper mapper;

    public UtilisateurServiceImpl(UtilisateurRepository repo, UtilisateurMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<UtilisateurDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UtilisateurDTO getById(Long id) {
        Utilisateur u = repo.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        return mapper.toDto(u);
    }

    @Override
    public UtilisateurDTO getByUsername(String username) {
        Utilisateur u = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        return mapper.toDto(u);
    }

    @Override
    public UtilisateurDTO create(UtilisateurDTO dto) {
        Utilisateur ent = mapper.toEntity(dto);
        Utilisateur saved = repo.save(ent);
        return mapper.toDto(saved);
    }

    @Override
    public UtilisateurDTO update(UtilisateurDTO dto) {
        Utilisateur ent = repo.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        ent.setUsername(dto.getUsername());
        ent.setActive(dto.isActive());
        Utilisateur updated = repo.save(ent);
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}