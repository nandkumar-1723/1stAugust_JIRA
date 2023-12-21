package ReadFiles;

import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadCreatedUserStory {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/JsonFiles/userStory.json");
        JSONParser js = new JSONParser();
        String requestBody = js.parse(fr).toString();
        System.out.println(requestBody);

    }

}
