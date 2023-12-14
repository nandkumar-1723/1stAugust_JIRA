package ReadJsons;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadSpecificKey {
    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/userStory.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        System.out.println(requestBody);

        //Json object concept
        JSONObject js = new JSONObject(requestBody);

        //Get the value of key
        String keyValue = js.getJSONObject("fields").getJSONObject("project").get("key").toString();
        System.out.println(keyValue);

        //get the value of name
        String name = js.getJSONObject("fields").getJSONObject("issuetype").get("name").toString();
        System.out.println(name);


    }
}
