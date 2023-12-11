package UserStory;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class CRUD_UserStory {

    @Test
    public void loginJira() throws IOException, ParseException {

        // to get the request body
        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/loginJira.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        //All methods comes from rest assured class.
        Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
                .contentType(ContentType.JSON)
                .when().post("/rest/auth/1/session")
                .then().extract().response();

        System.out.println(response.getStatusCode()); // to get the status code

        System.out.println(response.asString()); // to get the response
    }
}
