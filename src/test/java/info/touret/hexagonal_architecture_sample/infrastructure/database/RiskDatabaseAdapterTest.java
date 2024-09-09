package info.touret.hexagonal_architecture_sample.infrastructure.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus.DANGEROUS;
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
    private RiskEntity dangerousRiskEntity;

    @BeforeEach
    void setUp() {
        adapter = new RiskDatabaseAdapter(riskRepository);
        safeRiskEntity = new RiskEntity(1L, 500L, 1L, "SAFE", "SAFE");
        dangerousRiskEntity = new RiskEntity(1L, 1000L, 9999L, "DANGEROUS", "DANGEROUS");
    }

    @Test
    void should_return_SAFE() {
        when(riskRepository.findByAmountMinBeforeAndAmountMaxAfter(eq(100L), eq(100L))).thenReturn(Optional.of(safeRiskEntity));
        assertEquals(SAFE, adapter.getCorrespondingRiskStatus(100L).get().status());
    }
    @Test
    void should_return_DANGEROUS() {
        when(riskRepository.findByAmountMinBeforeAndAmountMaxAfter(eq(2000L), eq(2000L))).thenReturn(Optional.of(dangerousRiskEntity));
        assertEquals(DANGEROUS, adapter.getCorrespondingRiskStatus(2000L).get().status());
    }
}
