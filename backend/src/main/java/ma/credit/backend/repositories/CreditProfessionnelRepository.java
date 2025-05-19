package ma.credit.backend.repositories;

import ma.credit.backend.entities.CreditProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Long> {
}
