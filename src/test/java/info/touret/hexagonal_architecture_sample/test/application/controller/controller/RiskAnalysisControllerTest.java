package info.touret.hexagonal_architecture_sample.test.application.controller.controller;

import info.touret.hexagonal_architecture_sample.application.dto.RiskAnalysisDTO;
import info.touret.hexagonal_architecture_sample.domain.riskmanagement.model.RiskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql("classpath:/risks-data.sql")
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
        var analysisRequest = UriComponentsBuilder.fromHttpUrl(risksUrl).queryParam("amount", 400L).build();
        var riskAnalysis = testRestTemplate.getForEntity(analysisRequest.toUri(), RiskAnalysisDTO.class);
        var body = riskAnalysis.getBody();
        assertEquals(HttpStatus.OK.is2xxSuccessful(), riskAnalysis.getStatusCode().is2xxSuccessful());
        assertEquals(RiskStatus.SAFE, Objects.requireNonNull(body).status());
    }
    @Test
    void should_return_needs_authorization() throws URISyntaxException {
        var analysisRequest = UriComponentsBuilder.fromHttpUrl(risksUrl).queryParam("amount", 600L).build();
        var riskAnalysis = testRestTemplate.getForEntity(analysisRequest.toUri(), RiskAnalysisDTO.class);
        var body = riskAnalysis.getBody();
        assertEquals(HttpStatus.OK.is2xxSuccessful(), riskAnalysis.getStatusCode().is2xxSuccessful());
        assertEquals(RiskStatus.NEED_AUTHORIZATION, Objects.requireNonNull(body).status());
    }
    @Test
    void should_return_suspicious() throws URISyntaxException {
        var analysisRequest = UriComponentsBuilder.fromHttpUrl(risksUrl).queryParam("amount", 6000L).build();
        var riskAnalysis = testRestTemplate.getForEntity(analysisRequest.toUri(), RiskAnalysisDTO.class);
        var body = riskAnalysis.getBody();
        assertEquals(HttpStatus.OK.is2xxSuccessful(), riskAnalysis.getStatusCode().is2xxSuccessful());
        assertEquals(RiskStatus.SUSPICIOUS, Objects.requireNonNull(body).status());
    }
}
