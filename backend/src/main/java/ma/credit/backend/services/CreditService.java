package ma.credit.backend.services;

import ma.credit.backend.dtos.CreditDTO;
import java.util.List;

public interface CreditService {
    List<CreditDTO> getAllCredits();
    CreditDTO getCreditById(Long id);
    List<CreditDTO> searchCreditsByStatus(String statut);
    CreditDTO createCredit(CreditDTO dto);
    CreditDTO updateCredit(Long id, CreditDTO dto);
    void deleteCredit(Long id);
}