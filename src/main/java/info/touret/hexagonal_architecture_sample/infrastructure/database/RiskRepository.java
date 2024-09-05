package info.touret.hexagonal_architecture_sample.infrastructure.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends CrudRepository<RiskEntity, Long> {
    RiskEntity findByAmountMinGreaterThanAndAmountMaxBefore(Long amountMin, Long amountMax);
}
