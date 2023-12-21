package ReadPropertiesFile;

import java.io.*;
import java.util.*;

/**
 * @author Nandkumar Babar
 */
public class ReadProperites {
    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/1stAugust_JIRA/src/main/java/Files/credentials.properties");
        Properties prop = new Properties();
        prop.load(fr);
        System.out.println(prop.getProperty("url"));
        System.out.println(prop.getProperty("name"));
        System.out.println(prop.getProperty("city"));

    }
}
