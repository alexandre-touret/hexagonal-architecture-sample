package info.touret.hexagonal_architecture_sample.domain.riskmanagement.port;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskAnalysis;

import java.util.Optional;

public interface RiskPort {
    Optional<RiskAnalysis> getCorrespondingRiskStatus(Long amount);
}
