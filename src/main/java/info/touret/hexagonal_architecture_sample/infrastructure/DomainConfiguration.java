package info.touret.hexagonal_architecture_sample.infrastructure;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.service.RiskManagementService;
import info.touret.hexagonal_architecture_sample.infrastructure.database.RiskDatabaseAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public RiskManagementService createRiskManagementService(RiskDatabaseAdapter riskDatabaseAdapter) {
        return new RiskManagementService(riskDatabaseAdapter);
    }
}
