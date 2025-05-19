package ma.credit.backend.controllers;

import ma.credit.backend.dtos.RemboursementDTO;
import ma.credit.backend.entities.Remboursement;
import ma.credit.backend.repositories.RemboursementRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/remboursements")
public class RemboursementController {

    private final RemboursementRepository remboursementRepository;

    public RemboursementController(RemboursementRepository remboursementRepository) {
        this.remboursementRepository = remboursementRepository;
    }

    private RemboursementDTO convertToDto(Remboursement r) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(r.getId());
        //dto.setDateRemboursement(r.getDateRemboursement());
        //dto.setMontantRembourse(r.getMontantRembourse());
        dto.setCreditId(r.getCredit() != null ? r.getCredit().getId() : null);
        return dto;
    }

    private Remboursement convertToEntity(RemboursementDTO dto) {
        Remboursement r = new Remboursement();
        r.setId(dto.getId());
        //r.setDateRemboursement(dto.getDateRemboursement());
        //r.setMontantRembourse(dto.getMontantRembourse());
        // NOTE: credit should be set via service logic if needed
        return r;
    }

    @GetMapping
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        Optional<Remboursement> rOpt = remboursementRepository.findById(id);
        return rOpt.map(r -> ResponseEntity.ok(convertToDto(r)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        Remboursement remboursement = convertToEntity(remboursementDTO);
        Remboursement saved = remboursementRepository.save(remboursement);
        return new ResponseEntity<>(convertToDto(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemboursementDTO> updateRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO remboursementDTO) {
        Optional<Remboursement> rOpt = remboursementRepository.findById(id);
        if (rOpt.isPresent()) {
            Remboursement rToUpdate = rOpt.get();
            //rToUpdate.setDateRemboursement(remboursementDTO.getDateRemboursement());
            //rToUpdate.setMontantRembourse(remboursementDTO.getMontantRembourse());
            Remboursement updated = remboursementRepository.save(rToUpdate);
            return ResponseEntity.ok(convertToDto(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        if (remboursementRepository.existsById(id)) {
            remboursementRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
