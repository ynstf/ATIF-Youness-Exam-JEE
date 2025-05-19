package ma.credit.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public abstract class CreditDTO {
    private Long id;
    private Date dateDemande;
    private String statut;
    private Date dateAcceptation;
    private Double montant;
    private Integer duree;
    private Double tauxInteret;
    private Long clientId;
    private List<RemboursementDTO> remboursements;
}
