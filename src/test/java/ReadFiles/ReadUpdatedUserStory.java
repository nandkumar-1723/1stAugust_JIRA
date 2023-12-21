package ReadFiles;

import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadUpdatedUserStory {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/updateUserStory.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        System.out.println(requestBody);
    }
}
