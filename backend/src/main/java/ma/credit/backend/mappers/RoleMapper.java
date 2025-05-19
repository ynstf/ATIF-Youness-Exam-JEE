package ma.credit.backend.mappers;

import ma.credit.backend.entities.Role;
import ma.credit.backend.dtos.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDto(Role entity);
    Role toEntity(RoleDTO dto);
}
