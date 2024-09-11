package info.touret.hexagonal_architecture_sample.test.infrastructure.database;

import info.touret.hexagonal_architecture_sample.infrastructure.database.RiskDatabaseAdapter;
import info.touret.hexagonal_architecture_sample.infrastructure.database.RiskEntity;
import info.touret.hexagonal_architecture_sample.infrastructure.database.RiskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus.SUSPICIOUS;
import static info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus.SAFE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RiskDatabaseAdapterTest {

    private RiskDatabaseAdapter adapter;
    @Mock
    private RiskRepository riskRepository;
    private RiskEntity safeRiskEntity;
    private RiskEntity SUSPICIOUSRiskEntity;

    @BeforeEach
    void setUp() {
        adapter = new RiskDatabaseAdapter(riskRepository);
        safeRiskEntity = new RiskEntity(1L, 500L, 1L, "SAFE", "SAFE");
        SUSPICIOUSRiskEntity = new RiskEntity(1L, 1000L, 9999L, "SUSPICIOUS", "SUSPICIOUS");
    }

    @Test
    void should_return_SAFE() {
        when(riskRepository.findByAmountMinBeforeAndAmountMaxAfter(eq(100L), eq(100L))).thenReturn(Optional.of(safeRiskEntity));
        assertEquals(SAFE, adapter.getCorrespondingRiskStatus(100L).get().status());
    }
    @Test
    void should_return_SUSPICIOUS() {
        when(riskRepository.findByAmountMinBeforeAndAmountMaxAfter(eq(2000L), eq(2000L))).thenReturn(Optional.of(SUSPICIOUSRiskEntity));
        assertEquals(SUSPICIOUS, adapter.getCorrespondingRiskStatus(2000L).get().status());
    }
}
