package ma.credit.backend.mappers;

import ma.credit.backend.entities.*;
import ma.credit.backend.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = { RemboursementMapper.class })
public interface CreditMapper {
    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(target = "clientId", source = "client.id")
    CreditDTO toDto(Credit entity);

    @Mapping(target = "client.id", source = "clientId")
    Credit toEntity(CreditDTO dto);

    CreditPersonnelDTO toDto(CreditPersonnel entity);
    CreditPersonnel toEntity(CreditPersonnelDTO dto);

    CreditImmobilierDTO toDto(CreditImmobilier entity);
    CreditImmobilier toEntity(CreditImmobilierDTO dto);

    CreditProfessionnelDTO toDto(CreditProfessionnel entity);
    CreditProfessionnel toEntity(CreditProfessionnelDTO dto);
}
