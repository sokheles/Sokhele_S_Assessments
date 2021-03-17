package stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class Steps {

    private  static  final  String  BASE_URL  =  "https://dog.ceo/api/breeds";
    RequestSpecification request;
    private  static  Response response;
    private  static  String  generatedID;
    private static String name;


    @Given("I send a get request {string}")
    public void i_send_a_get_request(String resource) {
        RestAssured.baseURI  =  BASE_URL;
        request  =  RestAssured.given();
        request.header("Content-Type",  "application/json");
        response = request.get(resource);
    }

    @When("I get a response")
    public void i_get_a_response() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("A {string} message should be returned")
    public void a_message_should_be_returned(String messageString) {

        Assert.assertEquals(messageString, getJsonPath(response, "status"));

    }

    @Then("A {string} should be on the list returned")
    public void a_should_be_on_the_list_returned(String breed) {

        String[] breeds = response.jsonPath().getString("message").split(",");
        String b = null;
        for(String a :breeds){
            if(a.contains(breed)){
                 b = breed;
                 break;
            }

        }

        Assert.assertEquals(breed,b);
    }

    @Given("I send a get request for sub breed {string}")
    public void i_send_a_get_request_for_sub_breed(String resource) {
        RestAssured.baseURI  =  "https://dog.ceo/api/breed";
        request  =  RestAssured.given();
        request.header("Content-Type",  "application/json");
        response = request.get(resource);
    }

    @When("I retrieve all sub breeds for {string}")
    public void i_retrieve_all_sub_breeds_for(String breed) {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(breed+" not returned",response.asString().contains(breed));
    }

    @Then("I should see all sub breeds")
    public void i_should_see_all_sub_breeds() {
        String[] sub_breeds = response.jsonPath().getString("message").split(",");

        for (String a : sub_breeds) {
            System.out.println(a);
        }
    }

    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }


        //Task B

        @Given("that I send an xml request to base url")
        public void that_I_send_an_xml_request_to_base_url() {

            RestAssured.baseURI  =  "https://petstore.swagger.io/v2/pet";
            request  =  RestAssured.given().accept(ContentType.JSON);

        }

        @When("I pass parameter and {string}")
        public void i_pass_parameter_and(String pathAndParameter) {
            response = request.get(pathAndParameter);
           Assert.assertEquals(200,response.getStatusCode());
        }

        @Then("I validate that name {string} with categoryid {string} are is available in the list")
        public void i_validate_that_name_with_categoryid_are_is_available_in_the_list(String petName, String catID) {
            Assert.assertTrue("Category ID: "+catID+" with Name: "+petName+" was not found in the list",response.asString().contains("id\":"+catID+",\"name\":\"string\"},\"name\":\""+petName+"\",\"photoUrls"));

        }

    @Given("I add a pet with an auto generated name")
    public void i_add_a_pet_with_an_auto_generated_name() {

        RestAssured.baseURI  = "https://petstore.swagger.io"  ;
        request  =  RestAssured.given();
        request.header("Content-Type",  "application/json");
        name = generateRandomName();
        response  =  request.body("{ \"name\":\"" + name + "\", \"status\":\"" + "available" + "\"}")
                .post("/v2/pet");

    }

    @When("I confirm the pet was added")
    public void i_confirm_the_pet_was_added() {

        Assert.assertEquals(200, response.getStatusCode());

        System.out.println(response.asString());

        String[] ids = response.jsonPath().getString("id").split(",");
        generatedID = ids[0];

        Assert.assertTrue("id was not generated",generatedID.length()>5);
    }

    @Then("I should be able to search the pet using the id")
    public void i_should_be_able_to_search_the_pet_using_the_id() throws Exception{
        Thread.sleep(5000);
        response = request.get("/v2/pet/"+generatedID);
        Assert.assertEquals(200,response.getStatusCode());

        Assert.assertEquals(generatedID, getJsonPath(response, "id"));
        Assert.assertEquals(name, getJsonPath(response, "name"));
        System.out.println(response.asString());

    }

    public String generateRandomName(){

        Faker randomName = new Faker();

        return randomName.name().username();
    }

}