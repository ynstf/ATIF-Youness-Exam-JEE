package ma.credit.backend.services;

import ma.credit.backend.dtos.RemboursementDTO;
import ma.credit.backend.entities.Remboursement;
import ma.credit.backend.mappers.RemboursementMapper;
import ma.credit.backend.repositories.RemboursementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemboursementServiceImpl implements RemboursementService {

    private final RemboursementRepository repo;
    private final RemboursementMapper mapper;

    public RemboursementServiceImpl(RemboursementRepository repo, RemboursementMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<RemboursementDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO getById(Long id) {
        Remboursement ent = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement not found"));
        return mapper.toDto(ent);
    }

    @Override
    public List<RemboursementDTO> searchByType(String type) {
        return repo.findAll().stream()
                .filter(r -> r.getType().equalsIgnoreCase(type))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO create(RemboursementDTO dto) {
        Remboursement ent = mapper.toEntity(dto);
        Remboursement saved = repo.save(ent);
        return mapper.toDto(saved);
    }

    @Override
    public RemboursementDTO update(RemboursementDTO dto) {
        Remboursement ent = repo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Remboursement not found"));
        ent.setDate(dto.getDate());
        ent.setMontant(dto.getMontant());
        ent.setType(dto.getType());
        Remboursement updated = repo.save(ent);
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}