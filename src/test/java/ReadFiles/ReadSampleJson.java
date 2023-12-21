package ReadFiles;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadSampleJson {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/Files/sample.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        JSONObject js = new JSONObject(requestBody);

        //To get the KL rahul's ipl team name
        String teamName = js.getJSONArray("groupB").getJSONObject(0).get("team").toString();
        System.out.println(teamName);

        //To get the rcb team details
        String rcbDetails = js.getJSONArray("groupA").getJSONObject(0).toString();
        System.out.println(rcbDetails);

        //to get the no of arrays
        JSONArray noOfArrays = js.getJSONArray("groupA");
        System.out.println(noOfArrays.length());


    }
}
