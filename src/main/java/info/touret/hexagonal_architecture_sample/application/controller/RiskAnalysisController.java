package info.touret.hexagonal_architecture_sample.application.controller;

import info.touret.hexagonal_architecture_sample.application.dto.RiskAnalysisDTO;
import info.touret.hexagonal_architecture_sample.application.mapper.RiskAnalysisMapper;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.Payment;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.service.RiskManagementService;
import org.springframework.http.ProblemDetail;
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
        var optionalRiskAnalysis = riskManagementService.analyse(new Payment(amount));
        System.err.println("RISKS"+optionalRiskAnalysis);
        return optionalRiskAnalysis.map(riskAnalysis -> ResponseEntity.ok(riskAnalysisMapper.toRiskAnalysisDTO(riskAnalysis))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
