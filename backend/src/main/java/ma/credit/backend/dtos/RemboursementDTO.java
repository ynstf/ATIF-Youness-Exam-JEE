package ma.credit.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class RemboursementDTO {
    private Long id;
    private Date date;
    private Double montant;
    private String type;
    private Long creditId;
}
