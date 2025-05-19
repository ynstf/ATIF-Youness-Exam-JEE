package ma.credit.backend.repositories;

import ma.credit.backend.entities.CreditPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditPersonnelRepository extends JpaRepository<CreditPersonnel, Long> {
}
