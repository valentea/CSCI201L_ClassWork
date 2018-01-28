import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class readJSONFile {
    public static void main(String[] args) {

        while (true) {
            Scanner userInput = new Scanner(System.in);
            try{
                System.out.print("Enter input file name:");
                String inFileName = userInput.nextLine();
                try (FileInputStream jsonData = new FileInputStream(inFileName)) {
                    ObjectMapper studentMapper = new ObjectMapper();
                    DataBase dbOfStudents = studentMapper.readValue(jsonData, DataBase.class);
                    Menu menu = new Menu(dbOfStudents, userInput, inFileName);
                    menu.runMenu();

                }

                break;
            } catch (FileNotFoundException fileException) {
                System.out.println("That file could not be found.\n");
            } catch (JsonParseException jpException) {
                System.out.println("That file is not a well-formed JSON file.\n");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                break;
            }
        }
    }
}