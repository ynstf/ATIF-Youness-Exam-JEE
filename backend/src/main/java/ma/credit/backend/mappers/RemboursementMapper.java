package ma.credit.backend.mappers;

import ma.credit.backend.entities.Remboursement;
import ma.credit.backend.dtos.RemboursementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RemboursementMapper {
    @Mapping(target = "creditId", source = "credit.id")
    RemboursementDTO toDto(Remboursement entity);

    @Mapping(target = "credit.id", source = "creditId")
    Remboursement toEntity(RemboursementDTO dto);
}
