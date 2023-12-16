package TestCases;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.simple.parser.*;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.asserts.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class CRUD_Bug {

    @Test
    public void loginJira() throws Exception {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/loginJira.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
                .contentType(ContentType.JSON).when().post("/rest/auth/1/session")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),201);


    }

}
