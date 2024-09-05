package info.touret.hexagonal_architecture_sample.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus;

@JsonRootName("RiskAnalysis")
public record RiskAnalysisDTO(RiskStatus status, String message) {
}
