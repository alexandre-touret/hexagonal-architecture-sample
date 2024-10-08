package info.touret.hexagonal_architecture_sample.infrastructure.database;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskAnalysis;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.service.RiskPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus.*;

@Component
public class RiskDatabaseAdapter implements RiskPort {

    private final RiskRepository riskRepository;

    public RiskDatabaseAdapter(RiskRepository riskRepository) {
        this.riskRepository = riskRepository;
    }

    @Override
    public Optional<RiskAnalysis> getCorrespondingRiskStatus(Long amount) {
        var rule = riskRepository.findByAmountMinBeforeAndAmountMaxAfter(amount, amount);
        
        return rule.flatMap(riskEntity -> (switch (riskEntity.getRuleName()) {
            case "SAFE" -> Optional.of(new RiskAnalysis(SAFE, riskEntity.getMessage()));
            case "SUSPICIOUS" -> Optional.of(new RiskAnalysis(SUSPICIOUS, riskEntity.getMessage()));
            default -> Optional.of(new RiskAnalysis(NEED_AUTHORIZATION, riskEntity.getMessage()));
        }));
    }
}
