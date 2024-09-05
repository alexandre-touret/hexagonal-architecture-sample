package info.touret.hexagonal_architecture_sample.infrastructure.controller;

import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus;
import info.touret.hexagonal_architecture_sample.infrastructure.dto.RiskAnalysisDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class RiskAnalysisControllerTest {

    public static final String API_PREFIX = "/risks";
    @LocalServerPort
    private int port;
    private String risksUrl;


    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
        risksUrl = "http://127.0.0.1:" + port + API_PREFIX;
    }

    @Test
    void should_return_safe() throws URISyntaxException {
        var analysisRequest = UriComponentsBuilder.fromHttpUrl(risksUrl).queryParam("amount", 500L).build();
        var riskAnalysis = testRestTemplate.getForEntity(analysisRequest.toUri(), RiskAnalysisDTO.class);
        assertEquals(RiskStatus.SAFE, Objects.requireNonNull(riskAnalysis.getBody()).status());
    }
}
