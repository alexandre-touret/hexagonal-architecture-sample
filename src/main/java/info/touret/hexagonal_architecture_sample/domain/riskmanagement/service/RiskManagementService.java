package info.touret.hexagonal_architecture_sample.domain.riskmanagement.service;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.Payment;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskAnalysis;

import java.util.Optional;

public class RiskManagementService {

    private final RiskPort riskAdapter;

    public RiskManagementService(RiskPort riskAdapter) {
        this.riskAdapter = riskAdapter;
    }

    public Optional<RiskAnalysis> analyse(Payment payment) {
        return riskAdapter.getCorrespondingRiskStatus(payment.amount());
    }
}
