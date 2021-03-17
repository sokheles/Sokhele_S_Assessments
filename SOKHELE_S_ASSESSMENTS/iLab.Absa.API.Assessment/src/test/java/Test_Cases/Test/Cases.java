package Test_Cases.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;


public class Cases {

    @Test
    public static void getAllDogBreeds(){

        int responseBodySize = 0;
        Response getResponse = performGetRequest("https://dog.ceo/api/breeds/list/all");
        responseBodySize = getResponse.jsonPath().getString("message").length();
        Assert.assertTrue(responseBodySize != 0, "body is null");
    }

    @Test
    public static void verifyBreedIsWithinList(){

        String breed = "Retriever";
        Response getResponse = performGetRequest("https://dog.ceo/api/breeds/list/all");
        String[] breedsArray = getResponse.jsonPath().getString("message").split(",");

        Assert.assertTrue(getResponse.asString().contains(breed.toLowerCase()),breed+" is within the list");

    }

    @Test
    public static void getSubBreedsForBreed(){

        String breed = "retriever";

        Response getResponse = performGetRequest("https://dog.ceo/api/breed/"+breed+"/list");
        String[] breedsArray = getResponse.jsonPath().getString("message").split(",");

        Assert.assertTrue(breedsArray.length>1);
    }

    @Test
    public static void randomImageForSubBreed(){
        String sub_breed = "golden";
        String breed = "Retriever";
        Response getResponse = performGetRequest("https://dog.ceo/api/breed/"+breed.toLowerCase()+"/"+sub_breed+"/images/random");
        String[] breedsArray = getResponse.jsonPath().getString("message").split(",");

        Assert.assertTrue(breedsArray.length==1);

    }

    public static Response performGetRequest(String endpoint){
        RestAssured.defaultParser = Parser.JSON;

        return
        given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint)
                .then()
                .log()
                .body()
                .assertThat().statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

    }

}
