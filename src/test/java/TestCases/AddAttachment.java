package TestCases;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class AddAttachment {

    String cookieValue;
    String issueID;

    @Test(priority = 1)
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

        //To travel the json

        JSONObject js = new JSONObject(response.asString());

        cookieValue = "JSESSIONID=" + js.getJSONObject("session").get("value").toString();
        System.out.println(cookieValue);
    }

    @Test(priority = 2)
    public void createUserStory() throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/userStory.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
                .contentType(ContentType.JSON).header("Cookie", cookieValue).when().post("/rest/api/2/issue")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        //to travel the json
        JSONObject js = new JSONObject(response.asString());

        issueID = js.get("key").toString();
        System.out.println(issueID);

    }

    @Test(priority = 3)
    public void addAttachment(){

        File attachment = new File("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/Attachment.png");

        Response response = RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.MULTIPART)
                .header("Cookie", cookieValue).header("X-Atlassian-Token", "no-check")
                .multiPart("file", attachment).when().post("/rest/api/2/issue/"+issueID+"/attachments")
                .then().log().all().extract().response();

    }
}
