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

        //To get the KL rahul's ipl team name
        String teamName = js.getJSONArray("groupB").getJSONObject(0).get("team").toString();
        System.out.println(teamName);

        //To get the rcb team details
        String rcbDetails = js.getJSONArray("groupA").getJSONObject(0).toString();
        System.out.println(rcbDetails);

    }
}
