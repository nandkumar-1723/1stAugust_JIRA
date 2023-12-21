package TestCases;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.json.simple.parser.*;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.asserts.*;

import java.io.*;
import java.util.*;

/**
 * @author Nandkumar Babar
 */
public class CRUD_Bug {

    private String cookieValue;
    private String issueID;
    private String url;

    @Test(priority = 1)
    public void loginJira() throws Exception {
        //to get the url
        FileReader propfile = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/credentials.properties");
        Properties prop = new Properties();
        prop.load(propfile);
        url = prop.getProperty("url");

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/loginJira.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = RestAssured.given().baseUri(url).body(requestBody)
                .contentType(ContentType.JSON).when().post("/rest/auth/1/session")
                .then().log().all().extract().response();
        Assert.assertEquals(response.statusCode(),200);

        JSONObject js = new JSONObject(response.asString());
         cookieValue = "JSESSIONID="+js.getJSONObject("session").get("value").toString();

    }
    @Test(priority = 2)
    public void createBug() throws IOException, ParseException {

    FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/Bug.json");
    JSONParser jp =new JSONParser();
        String requestBody = jp.parse(fr).toString();


        Response response = RestAssured.given().baseUri(url).body(requestBody)
                .contentType(ContentType.JSON).header("Cookie", cookieValue)
                .when().post("/rest/api/2/issue").then().log().all().extract().response();


        Assert.assertEquals(response.statusCode(),201);

        JSONObject js = new JSONObject(response.asString());
        issueID =  js.get("key").toString();
        System.out.println(issueID);

    }

    @Test(priority = 3)
    public void getBug(){

        Response response = RestAssured.given().baseUri(url).contentType(ContentType.JSON)
                .header("Cookie", cookieValue).when().get("/rest/api/2/issue/"+issueID+"")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),200);

    }

    @Test(priority = 4)
    public void updateBug() throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/Bug.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        JSONObject js = new JSONObject(requestBody);
        js.getJSONObject("fields").put("summary","updating the bug on 17th december");

        Response response = RestAssured.given().baseUri(url).body(js.toString())
                .contentType(ContentType.JSON).header("Cookie", cookieValue)
                .when().put("/rest/api/2/issue/"+issueID+"").then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),204);

    }

    @Test(priority = 5)
    public void getUpdatedBug(){

        Response response = RestAssured.given().baseUri(url).contentType(ContentType.JSON)
                .header("Cookie", cookieValue).when().get("/rest/api/2/issue/"+issueID+"")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),200);

    }

    @Test(priority = 6)
    public void deleteBug(){

        Response response = RestAssured.given().baseUri(url).contentType(ContentType.JSON)
                .header("Cookie", cookieValue).when().delete("/rest/api/2/issue/"+issueID+"")
                .then().log().all().extract().response();

        Assert.assertEquals(response.statusCode(),204);

    }

}
