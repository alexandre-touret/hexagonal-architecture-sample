package info.touret.hexagonal_architecture_sample.application.mapper;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskAnalysis;
import info.touret.hexagonal_architecture_sample.application.dto.RiskAnalysisDTO;
import org.mapstruct.Mapper;

@Mapper
public interface RiskAnalysisMapper {

    RiskAnalysis toRiskAnalysis(RiskAnalysisDTO riskAnalysisDTO);

    RiskAnalysisDTO toRiskAnalysisDTO(RiskAnalysis riskAnalysis);
}
