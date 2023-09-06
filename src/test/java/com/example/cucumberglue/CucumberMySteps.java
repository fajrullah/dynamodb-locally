package com.example.cucumberglue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
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

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberMySteps {
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

    @Then("response status code is not present")
    public void thenStatusCodeeIsNotPresent() {
        Assertions.assertNull(response);
    }

    @Then("returned string should be {string}")
    public void thenStringIs(String expected) {
        Assertions.assertEquals(expected, response.getBody());
//        assertThat("Returned string is " + expected,
//                expected.equalsIgnoreCase(lastResponse.getBody()));
    }

    private List<Map<String, String>> ships;
    RestTemplate restTemplate = new RestTemplate();

    @Given("We have gaffa taped the following spaceships together")
    public void weHaveGaffaTapedTheFollowingSpaceshipsTogether(DataTable shipsGaffaTaped) {
        List<Map<String, String>> maps = shipsGaffaTaped.asMaps();
        System.out.println(shipsGaffaTaped);
        System.out.println(maps);
        ships = maps;
    }



}
