package hellocucumber;


import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class StepDefinitions {

    private Scenario scenario;
    private Response response;
    private final String BASE_URL = "https://search-admin-dev-mamb5phriq-uc.a.run.app";


    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("Get Call to {string}")
    public void get_call_to_url(String url) throws Exception {
        RestAssured.baseURI = BASE_URL;

        RequestSpecification req = given();
        req.header("authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB3ZWJsaW5rdGVjaHMuY29tIiwiZXhwIjoxNjYyNDcxNzQxLCJpYXQiOjE2NjI0NjgxNDF9.3I3QE1JhkBuj0tZf-X7jn1SvinS_IklcPKEQQXMlrNf_tFXbJZDAZuvbDvKg9PfZOoE03gApMBnA3X7EyvHUTg");
        response = req.when().get(new URI(url));
    }

    @Then("Response Code {string} is validated")
    public void response_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assertions.assertEquals(responseMessage, responseCode+"");
    }

    private String body = "{\"email\":\"admin@weblinktechs.com\", \"pass\":\"!adg@u^s5FE43q\"}";
    private String token="";
    @Before
    public void setUp(){
            given().contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post(BASE_URL)
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.SC_OK);

            token = given().contentType(ContentType.JSON)
                    .body(body)
                    .when()
                    .post("https://search-admin-dev-mamb5phriq-uc.a.run.app/authenticate")
                    .jsonPath()
                    .get("token");

        System.out.println("toekslkdfsldjfns       : "+token);
    }


}