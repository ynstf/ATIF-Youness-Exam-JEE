package ma.credit.backend.services;

import ma.credit.backend.dtos.UtilisateurDTO;
import java.util.List;

public interface UtilisateurService {
    List<UtilisateurDTO> getAll();
    UtilisateurDTO getById(Long id);
    UtilisateurDTO getByUsername(String username);
    UtilisateurDTO create(UtilisateurDTO dto);
    UtilisateurDTO update(UtilisateurDTO dto);
    void delete(Long id);
}