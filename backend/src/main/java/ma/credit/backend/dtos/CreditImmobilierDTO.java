package ma.credit.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreditImmobilierDTO extends CreditDTO {
    private String typeBien;
}
