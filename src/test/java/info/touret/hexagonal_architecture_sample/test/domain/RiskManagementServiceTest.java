package info.touret.hexagonal_architecture_sample.test.domain;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.service.RiskManagementService;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.service.RiskPort;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.Payment;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskAnalysis;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus.NEED_AUTHORIZATION;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RiskManagementServiceTest {

    private RiskManagementService riskManagementService;

    @Mock
    private RiskPort riskAdapter;

    @BeforeEach
    void setUp() {

        riskManagementService = new RiskManagementService(riskAdapter);

    }

    @Test
    void should_return_authorized() throws Exception {
        when(riskAdapter.getCorrespondingRiskStatus(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new RiskAnalysis(RiskStatus.SAFE, "Safe")));
        Assertions.assertEquals(RiskStatus.SAFE, riskManagementService.analyse(new Payment(20L)).get().status());
    }

    @Test
    void should_return_SUSPICIOUS() throws Exception {
        when(riskAdapter.getCorrespondingRiskStatus(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new RiskAnalysis(RiskStatus.SUSPICIOUS, "SUSPICIOUS")));
        Assertions.assertEquals(RiskStatus.SUSPICIOUS, riskManagementService.analyse(new Payment(200000L)).get().status());
    }

    @Test
    void should_return_needs_authorization() throws Exception {
        when(riskAdapter.getCorrespondingRiskStatus(ArgumentMatchers.anyLong())).thenReturn(Optional.of(new RiskAnalysis(NEED_AUTHORIZATION, "SUSPICIOUS")));
        Assertions.assertEquals(NEED_AUTHORIZATION, riskManagementService.analyse(new Payment(1500L)).get().status());
    }
}
