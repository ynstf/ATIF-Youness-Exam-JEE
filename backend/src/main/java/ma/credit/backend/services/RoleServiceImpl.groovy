package ma.credit.backend.services;

import ma.credit.backend.dtos.RoleDTO;
import ma.credit.backend.entities.Role;
import ma.credit.backend.mappers.RoleMapper;
import ma.credit.backend.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repo;
    private final RoleMapper mapper;

    public RoleServiceImpl(RoleRepository repo, RoleMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<RoleDTO> getAll() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getById(Long id) {
        Role r = repo.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        return mapper.toDto(r);
    }

    @Override
    public RoleDTO getByName(String roleName) {
        Role r = repo.findByRoleName(roleName);
        return mapper.toDto(r);
    }

    @Override
    public RoleDTO create(RoleDTO dto) {
        Role ent = mapper.toEntity(dto);
        Role saved = repo.save(ent);
        return mapper.toDto(saved);
    }

    @Override
    public RoleDTO update(RoleDTO dto) {
        Role ent = repo.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Role not found"));
        ent.setRoleName(dto.getRoleName());
        Role updated = repo.save(ent);
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
