package info.touret.hexagonal_architecture_sample.infrastructure.database;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.port.RiskPort;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskAnalysis;
import org.springframework.stereotype.Component;

import static info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus.*;

@Component
public class RiskDatabaseAdapter implements RiskPort {

    private final RiskRepository riskRepository;

    public RiskDatabaseAdapter(RiskRepository riskRepository) {
        this.riskRepository = riskRepository;
    }

    @Override
    public RiskAnalysis getCorrespondingRiskStatus(Long amount) {
        var rule = riskRepository.findByAmountMinGreaterThanAndAmountMaxBefore(amount,amount);

        return switch (rule.getRuleName()) {
            case "SAFE" -> new RiskAnalysis(SAFE, rule.getMessage());
            case "DANGEROUS" -> new RiskAnalysis(DANGEROUS, rule.getMessage());
            default -> new RiskAnalysis(NEED_AUTHORIZATION, rule.getMessage());
        };
    }
}
