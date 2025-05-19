package ma.credit.backend.services;

import ma.credit.backend.dtos.RoleDTO;
import java.util.List;

public interface RoleService {
    List<RoleDTO> getAll();
    RoleDTO getById(Long id);
    RoleDTO getByName(String roleName);
    RoleDTO create(RoleDTO dto);
    RoleDTO update(RoleDTO dto);
    void delete(Long id);
}