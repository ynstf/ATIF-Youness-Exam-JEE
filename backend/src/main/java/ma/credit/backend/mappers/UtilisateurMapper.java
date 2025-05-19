package ma.credit.backend.mappers;

import ma.credit.backend.entities.Utilisateur;
import ma.credit.backend.dtos.UtilisateurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))")
    UtilisateurDTO toDto(Utilisateur user);

    @Mapping(target = "roles", ignore = true) // vous pouvez gérer l'association roles séparément
    Utilisateur toEntity(UtilisateurDTO dto);
}
