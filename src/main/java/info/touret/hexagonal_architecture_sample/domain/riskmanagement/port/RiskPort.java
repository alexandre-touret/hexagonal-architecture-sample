package info.touret.hexagonal_architecture_sample.domain.riskmanagement.port;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskAnalysis;

public interface RiskPort {
    RiskAnalysis getCorrespondingRiskStatus(Long amount);
}
