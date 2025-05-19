package ma.credit.backend.services;

import ma.credit.backend.dtos.CreditDTO;
import ma.credit.backend.entities.Credit;
import ma.credit.backend.mappers.CreditMapper;
import ma.credit.backend.repositories.CreditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepo;
    private final CreditMapper creditMapper;

    public CreditServiceImpl(CreditRepository creditRepo, CreditMapper creditMapper) {
        this.creditRepo = creditRepo;
        this.creditMapper = creditMapper;
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepo.findAll().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        Credit credit = creditRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        return creditMapper.toDto(credit);
    }

    @Override
    public List<CreditDTO> searchCreditsByStatus(String statut) {
        return creditRepo.findAll().stream()
                .filter(c -> c.getStatut().equalsIgnoreCase(statut))
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO createCredit(CreditDTO dto) {
        Credit entity = creditMapper.toEntity(dto);
        Credit saved = creditRepo.save(entity);
        return creditMapper.toDto(saved);
    }

    @Override
    public CreditDTO updateCredit(Long id, CreditDTO dto) {
        Credit existing = creditRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        // mettre à jour les champs spécifiques si nécessaire
        Credit updated = creditRepo.save(existing);
        return creditMapper.toDto(updated);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepo.deleteById(id);
    }
}