package ma.credit.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreditProfessionnelDTO extends CreditDTO {
    private String motif;
    private String raisonSocialeEntreprise;
}
