package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;


    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {

        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithPostHttpRequest(String path, String method)  {

        APIResources ress = APIResources.valueOf(path);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).build();
        if(method.equalsIgnoreCase("POST")) {
            response = res.when().post(ress.getPath());
        }else if(method.equalsIgnoreCase("GET")) {
            response = res.when().get(ress.getPath());
        }
    }


    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String Expectedvalue) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(getJsonPath(response,keyValue),Expectedvalue);
    }

    @Given("DeletePlace Payload")
    public void deletePlacePayload() throws IOException {

        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        place_id=getJsonPath(response,"place_id");
        res= given().spec(requestSpecification()).queryParam("place_id",place_id);
        userCallsWithPostHttpRequest(resource,"GET");
        String actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedName);
    }
}
