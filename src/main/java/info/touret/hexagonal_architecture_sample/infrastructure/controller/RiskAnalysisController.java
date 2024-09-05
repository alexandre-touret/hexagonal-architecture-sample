package info.touret.hexagonal_architecture_sample.infrastructure.controller;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.Payment;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.service.RiskManagementService;
import info.touret.hexagonal_architecture_sample.infrastructure.dto.RiskAnalysisDTO;
import info.touret.hexagonal_architecture_sample.infrastructure.mapper.RiskAnalysisMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskAnalysisController {

    private final RiskManagementService riskManagementService;
    private final RiskAnalysisMapper riskAnalysisMapper;

    public RiskAnalysisController(RiskManagementService riskManagementService, RiskAnalysisMapper riskAnalysisMapper) {
        this.riskManagementService = riskManagementService;
        this.riskAnalysisMapper = riskAnalysisMapper;
    }

    @GetMapping("/risks")
    public ResponseEntity<RiskAnalysisDTO> getRiskAnalysis(@RequestParam("amount") Long amount) {
        return ResponseEntity.ok(riskAnalysisMapper.toRiskAnalysisDTO(riskManagementService.analyse(new Payment(amount))));
    }
}
