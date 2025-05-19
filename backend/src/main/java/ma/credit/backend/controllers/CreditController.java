package ma.credit.backend.controllers;

import ma.credit.backend.entities.Credit;
import ma.credit.backend.dtos.CreditDTO;
import ma.credit.backend.repositories.CreditRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credits")
public class CreditController {

    private final CreditRepository creditRepository;

    public CreditController(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    private CreditDTO convertToDto(Credit credit) {
        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setMontant(credit.getMontant());
        //dto.setTaux(credit.getTaux());
        dto.setDuree(credit.getDuree());
        dto.setClientId(credit.getClient().getId());
        return dto;
    }

    private Credit convertToEntity(CreditDTO dto) {
        Credit credit = new Credit();
        credit.setId(dto.getId());
        credit.setMontant(dto.getMontant());
        //credit.setTaux(dto.getTaux());
        credit.setDuree(dto.getDuree());
        // Assuming Client is already fetched by service layer or another method
        return credit;
    }

    @GetMapping
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        Optional<Credit> creditOpt = creditRepository.findById(id);
        return creditOpt.map(credit -> ResponseEntity.ok(convertToDto(credit)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreditDTO> createCredit(@RequestBody CreditDTO creditDTO) {
        Credit credit = convertToEntity(creditDTO);
        Credit savedCredit = creditRepository.save(credit);
        return new ResponseEntity<>(convertToDto(savedCredit), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditDTO> updateCredit(@PathVariable Long id, @RequestBody CreditDTO creditDTO) {
        Optional<Credit> creditOpt = creditRepository.findById(id);
        if (creditOpt.isPresent()) {
            Credit creditToUpdate = creditOpt.get();
            creditToUpdate.setMontant(creditDTO.getMontant());
            //creditToUpdate.setTaux(creditDTO.getTaux());
            creditToUpdate.setDuree(creditDTO.getDuree());
            Credit updatedCredit = creditRepository.save(creditToUpdate);
            return ResponseEntity.ok(convertToDto(updatedCredit));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        if (creditRepository.existsById(id)) {
            creditRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
