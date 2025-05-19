package ma.credit.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CREDIT", discriminatorType = DiscriminatorType.STRING, length = 20)
public class Credit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    private String statut; // En cours, Accepté, rejeté
    @Temporal(TemporalType.DATE)
    private Date dateAcceptation;
    private Double montant;
    private Integer duree;
    private Double tauxInteret;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements;
}