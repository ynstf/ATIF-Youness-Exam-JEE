package ma.credit.backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PROFESSIONNEL")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSocialeEntreprise;
}