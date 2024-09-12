package info.touret.hexagonal_architecture_sample;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.service.RiskManagementService;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.service.RiskPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public RiskManagementService createRiskManagementService(RiskPort riskPort) {
        return new RiskManagementService(riskPort);
    }
}
