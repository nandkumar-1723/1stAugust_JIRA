package updateJson;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class updateSampleJson {
    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/sample.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();


        JSONObject js = new JSONObject(requestBody);
        js.getJSONArray("groupA").getJSONObject(0).put("salary","20cr");
        js.getJSONArray("groupA").getJSONObject(1).put("age",36);

        System.out.println(js);

    }
}
