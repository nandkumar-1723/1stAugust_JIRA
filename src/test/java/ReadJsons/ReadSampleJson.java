package ReadJsons;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadSampleJson {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/sample.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        JSONObject js = new JSONObject(requestBody);

        //Get the value of age
        String age = js.getJSONArray("sales").getJSONObject(1).get("age").toString();
        System.out.println(age);

        //Get the value from the accounting
        String firstName = js.getJSONArray("accounting").getJSONObject(0).get("firstName").toString();
        System.out.println(firstName);

        //Get the accounting value from index 1
        String indexOne = js.getJSONArray("accounting").getJSONObject(1).toString();
        System.out.println(indexOne);

    }
}
