package com.example.cucumberglue;

import com.example.entity.Student;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class StudentControllerSteps {
    public class RunCucumberTest {
    }
    String port = "8080";
    ResponseEntity<String> response;

    Student student;

    private HttpHeaders headers;

    @When("I call endpoint {string}")
    public void whenClientCalls(String url) {
        try {
            response = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, null,
                    String.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            httpClientErrorException.printStackTrace();
        }
    }

    @When("I call update endpoint {string} JSON body:")
    public void whenClientCallsUpdate(String url, String jsonBody) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            response = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.PUT, requestEntity,
                    String.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            httpClientErrorException.printStackTrace();
        }
    }

    @When("I call endpoint to save {string} JSON body:")
    public void whenClientCallsSaved(String url, String jsonBody) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            response = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.POST, requestEntity,
                    String.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            httpClientErrorException.printStackTrace();
        }
    }

    @When("I call delete endpoint {string}")
    public void whenClientCallDelete(String url) {
        try {
            response = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.DELETE, null,
                    String.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            httpClientErrorException.printStackTrace();
        }
    }

    @Then("response status code is {int}")
    public void thenStatusCode(int expected) {
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatusCode());
        assertThat("status code is " + expected,
                response.getStatusCodeValue() == expected);
    }

    @Then("response media type is application JSON")
    public void thenMediaTypeIsApplicationJson() {
        String contentType = response.getHeaders().getFirst("Content-Type");
        Assertions.assertNotNull(contentType);
        Assertions.assertTrue(contentType.toLowerCase().contains("json"));
    }

    @Then("response media type is not set")
    public void thenMediaTypeIsApplicationNotSet() {
        String contentType = response.getHeaders().getFirst("Content-Type");
        Assertions.assertNull(contentType);
    }

    @Then("response after delete should be {string}")
    public void responseAfterDelete(String message) {
        Assertions.assertEquals(message, response.getBody(), "Response body should match");
    }

}
