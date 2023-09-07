package com.example.cucumberglue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberSteps {
    public class RunCucumberTest {
    }

    @LocalServerPort
    String port;
    ResponseEntity<String> response;


    @When("the client calls endpoint {string}")
    public void whenClientCalls(String url) {
        try {
            response = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, null,
                    String.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            httpClientErrorException.printStackTrace();
        }
    }

    @Then("response status code is {int}")
    public void thenStatusCode(int expected) {
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatusCode());
        assertThat("status code is" + expected,
                response.getStatusCodeValue() == expected);
    }

    @Then("response media type is application JSON")
    public void thenMediaTypeIsApplicationJson() {
        String contentType = response.getHeaders().getFirst("Content-Type");
        Assertions.assertNotNull(contentType);
        Assertions.assertTrue(contentType.toLowerCase().contains("json"));
    }

}
