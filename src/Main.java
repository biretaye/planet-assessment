import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter();
        fileWriter.writeOutputFile("testfile.txt", "lastname");
    }

}
