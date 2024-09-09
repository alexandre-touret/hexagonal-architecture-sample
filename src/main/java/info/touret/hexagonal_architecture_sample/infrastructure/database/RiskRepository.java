package info.touret.hexagonal_architecture_sample.infrastructure.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiskRepository extends CrudRepository<RiskEntity, Long> {
    Optional<RiskEntity> findByAmountMinBeforeAndAmountMaxAfter(Long amountMin, Long amountMax);
}
