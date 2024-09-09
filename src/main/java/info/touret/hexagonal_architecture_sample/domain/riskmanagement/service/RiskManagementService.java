package info.touret.hexagonal_architecture_sample.domain.riskmanagement.service;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.Payment;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskAnalysis;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.port.RiskPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class RiskManagementService {

    public static final int MAX_AUTHORIZED = 1000;
    public static final int MIN_DANGEROUS = 2000;
    private final RiskPort riskAdapter;

    public RiskManagementService(RiskPort riskAdapter) {
        this.riskAdapter = riskAdapter;
    }

    public Optional<RiskAnalysis> analyse(Payment payment) {
        return riskAdapter.getCorrespondingRiskStatus(payment.amount());
    }
}
