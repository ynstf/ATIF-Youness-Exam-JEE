package ma.credit.backend.repositories;

import ma.credit.backend.entities.CreditImmobilier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
}
