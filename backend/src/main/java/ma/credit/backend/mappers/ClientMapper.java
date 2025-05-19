package ma.credit.backend.mappers;

import ma.credit.backend.entities.Client;
import ma.credit.backend.dtos.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CreditMapper.class })
public interface ClientMapper {
    ClientDTO toDto(Client entity);
    Client toEntity(ClientDTO dto);
}
