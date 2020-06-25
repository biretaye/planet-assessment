import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class FileReader {
    String fileType;
    String firstname;
    String lastname;
    String startDate;
    String address1;
    String address2;
    String city;
    String state;
    String country;
    String zip;
    String rawDate;

    public static Map<String, Map<String, String>> fileMapper = new HashMap<>();
    HashMap<String, String> file = new HashMap<>();
    List filesList = new ArrayList();

    //Returns the list of key,value pairs that contain all the file attributes.
    public List writeContent() {
        return filesList;
    }

    //Reads and processes the input file
    public void readInputFile(String filename, String sorter) {
        Path filePath = Paths.get(filename);
        try {
            File inputFile = new File(filename);
            if (inputFile.exists()) {
                try (Scanner reader = new Scanner(inputFile)) {
                    if (inputFile.length() != 0) {
                        fileType = Files.lines(filePath).findFirst().get();
                        reader.nextLine();
                        while (reader.hasNextLine()) {
                            String data = reader.nextLine();
                            //Check file type
                            if (fileType.equals("1")) {
                                parseLineForSpaced(data);
                            }
                            if (fileType.equals("2")) {
                                parseLineForComma(data);
                            }
                        }
                        //Checks if the sorting attribute specified is valid or not before sorting.
                        if (sorter == "firstname" || sorter == "lastname" || sorter == "date") {
                            //Sorts the list of key,value pairs for each file 
                            Collections.sort(filesList, new Comparator<HashMap<String, String>>() {
                                public int compare(HashMap<String, String> one, HashMap<String, String> two) {
                                    return one.get(sorter).toLowerCase().compareTo(two.get(sorter).toLowerCase());
                                }
                            });
                        } else {
                            System.out.println("Invalid sorting attribute, default sorting selected.");
                        }

                    } else {
                        System.out.println("File is empty.");
                    }
                }
            } else {
                System.out.println("Input file does not exist");
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Error while reading file");
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Extracts elements from comma separated file
    public void parseLineForComma(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");
        if (scanner.hasNext()) {
            firstname = scanner.next();
            lastname = scanner.next();
            rawDate = scanner.next();
            startDate = reformatDate(rawDate);
            address1 = scanner.next();
            address2 = scanner.next();
            city = scanner.next();
            state = scanner.next();
            country = scanner.next();
            zip = scanner.next();
            populateFile();
        }
    }

    //Extracts elements from comma separated file
    public void parseLineForSpaced(String line) {
        firstname = line.substring(0, 9).trim();
        lastname = line.substring(10, 26).trim();
        startDate = reformatDate(line.substring(27, 35).trim());
        rawDate = line.substring(27, 35).trim();
        address1 = line.substring(35, 44).trim();
        address2 = line.substring(45, 54).trim();
        city = line.substring(55, 64).trim();
        state = line.substring(65, 67).trim();
        country = line.substring(68, 69).trim();
        zip = line.substring(70).trim();
        populateFile();
    }

    //Reformats the date
    public String reformatDate(String date) {
        String arrangedDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        LocalDate localDate = LocalDate.parse(arrangedDate);

        int day = localDate.getDayOfMonth();
        Month month = localDate.getMonth();
        int year = localDate.getYear();
        String reformattedDate = month + " " + day + "," + year;
        return reformattedDate;
        //System.out.println(reformattedDate);
    }

    //stores extracted values for the elements and adds it to a list of hashmaps that is used for storing and sorting.
    public void populateFile() {
        file = new HashMap<>();
        file.put("fileType", fileType);
        file.put("firstname", firstname);
        file.put("lastname", lastname);
        file.put("date", rawDate);
        file.put("startdate", startDate);
        file.put("address1", address1);
        file.put("address2", address2);
        file.put("city", city);
        file.put("state", state);
        file.put("country", country);
        file.put("zip", zip);

        filesList.add(file);
    }
}

