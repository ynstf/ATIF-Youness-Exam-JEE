package ma.credit.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class UtilisateurDTO {
    private Long id;
    private String username;
    private boolean active;
    private List<String> roles;
}
