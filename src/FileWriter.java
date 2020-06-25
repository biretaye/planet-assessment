import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileWriter {
    List contentList = new ArrayList();
    Map<String, String> tempContent = new HashMap<>();
    int fileIndex = 0;
    String content = "";

    public void writeOutputFile(String filename, String sorter) throws IOException {
        Path fileName = Path.of("outputFile.txt");
        FileReader fileReader = new FileReader();
        fileReader.readInputFile(filename, sorter);
        contentList = fileReader.writeContent();
        //Loops through the content written while reading file in the FileReader class and rearranges the content.
        for (int i = 0; i < contentList.size(); i++) {
            fileIndex = i + 1;
            tempContent = (Map<String, String>) contentList.get(i);
            if (tempContent.get("country").equals("")){
                //setting default value if country is not specified
                tempContent.put("country", "USA");
            }
            if (tempContent.get("state").equals("")){
                //setting default value if state is not specified
                tempContent.put("state", "CA");
            }
            content = content + "\n" + fileIndex + "\n"
                    + tempContent.get("firstname") + " " + tempContent.get("lastname") + ",(" + tempContent.get("startdate") + ")\n"
                    + tempContent.get("address1") + " " + tempContent.get("address2") + ",\n"
                    + tempContent.get("city") + "," + tempContent.get("state") + "\n"
                    + tempContent.get("country") + "," + tempContent.get("zip");
            Files.writeString(fileName, content);
        }
    }
}
