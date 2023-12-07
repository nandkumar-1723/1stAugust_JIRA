package ReadJsons;

import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadLoginFile {
    @Test
    public void loginToJira() throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/loginJira.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        System.out.println(requestBody);


    }
}
