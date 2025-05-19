package ma.credit.backend.services;

import ma.credit.backend.dtos.RemboursementDTO;
import java.util.List;

public interface RemboursementService {
    List<RemboursementDTO> getAll();
    RemboursementDTO getById(Long id);
    List<RemboursementDTO> searchByType(String type);
    RemboursementDTO create(RemboursementDTO dto);
    RemboursementDTO update(RemboursementDTO dto);
    void delete(Long id);
}