import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Menu {

    private DataBase dataBase;
    private boolean exit = false;
    private Scanner userInput;
    private String inputFileName;
    private boolean fileUpdated = true;

    class invalidOptionError extends Exception {
        invalidOptionError() {
            super("Invalid menu selection.");
        }
    }


    Menu(DataBase temp, Scanner userInput, String inputFileName) {
        this.dataBase = temp;
        this.userInput = userInput;
        this.inputFileName = inputFileName;
    }

    public void runMenu() {
        while (!exit) {
            printMenu();
            int choice = getMenuChoice(userInput);
            performAction(choice, userInput);
        }
        if (!fileUpdated) {
            System.out.println("Changes have been made since the file was last saved.");
            System.out.println("1) Save File");
            System.out.println("2) Discard Changes");
            System.out.println("Would you like to save file before exiting?");
            int saveFileSelect = userInput.nextInt();
            if (saveFileSelect == 1) {
                performAction(6, userInput);
            } else if (saveFileSelect == 2) {
                System.out.println("File will not be saved.");
            }
        }
        System.out.println("Thanks for using this program. \nTerminate.");
    }

    private void printMenu() {
        System.out.println(
                "1) Display Roster\n" +
                        "2) Add Student\n" +
                        "3) Remove Student\n" +
                        "4) Add Grade\n" +
                        "5) Sort Roster\n" +
                        "6) Write File\n" +
                        "7) Exit");
    }

    private int getMenuChoice(Scanner userInput) {
        int menuItem;
        while (true) {
            System.out.print("What would you like to do? ");
            try {
                menuItem = Integer.parseInt(userInput.nextLine());
                if (menuItem < 1 || menuItem > 7) {
                    throw new invalidOptionError();
                }
                break;
            } catch (invalidOptionError wrongMenuSelection) {
                System.out.println(wrongMenuSelection.getMessage());
            } catch (NumberFormatException notANumber) {
                System.out.println("Only enter menu item numbers.");
            }
        }
        System.out.println();
        return menuItem;
    }

    private void performAction(int menuItem, Scanner userInput) {
        try {
            switch (menuItem) {
                case 1: {
                    for (Student temp : dataBase.getStudents()) {
                        System.out.println(temp);
                    }
                    System.out.println();
                }
                break;
                case 2: {
                    String firstName;
                    String lastName;
                    while (true) {
                        System.out.println("Enter new student name (FirstName LastName): ");
                        String name = userInput.nextLine();
                        String parts[] = name.split(" ");
                        if (parts.length == 2) {
                            firstName = parts[0];
                            lastName = parts[1];
                            break;
                        } else {
                            System.out.println("Invalid, must have only first and last name,");
                        }
                    }
                    Name newStudentName = new Name(firstName, lastName);
                    dataBase.addNewStudent(newStudentName);
                    fileUpdated = false;
                }
                break;
                case 3: {
                    Student selectedStudent = selectStudent();
                    dataBase.removeStudent(selectedStudent);
                    fileUpdated = false;
                }
                break;
                case 4: {
                    Student selectedStudent = selectStudent();
                    System.out.print("Add new grade value: ");
                    int newGrade = Integer.parseInt(userInput.nextLine());
                    dataBase.addGrade(selectedStudent, newGrade);
                    fileUpdated = false;
                }
                break;
                case 5: {
                    System.out.println("1) Alphabetically");
                    System.out.println("2) Average Grade\n");
                    System.out.println("How would you like to sort the roster?");
                    int selection = Integer.parseInt(userInput.nextLine());
                    if (selection != 1 && selection != 2) {
                        throw new invalidOptionError();
                    }
                    dataBase.sort(selection);
                    fileUpdated = false;
                }
                break;
                case 6: {
                    final ObjectMapper outputMapper = new ObjectMapper();
                    String temp = outputMapper.writeValueAsString(dataBase);

                    try (FileWriter outputFile = new FileWriter(inputFileName); PrintWriter outputInfo = new PrintWriter(outputFile)) {
                        outputInfo.println(temp);
                        outputInfo.flush();
                    }
                    fileUpdated = true;
                    System.out.println("File has been updated.\n");
                }
                break;
                case 7: {
                    exit = true;
                }
                break;
            }
        } catch (invalidOptionError wrongMenuSelection) {
            System.out.println(wrongMenuSelection.getMessage());
            performAction(menuItem, userInput);
        } catch (NumberFormatException notANumber) {
            System.out.println("Only enter menu item numbers.");
            performAction(menuItem, userInput);
        } catch (IOException e) {
            e.getMessage();
        }

    }

    private Student selectStudent() {
        int i = 1;
        for (Student temp : dataBase.getStudents()) {
            System.out.print(i + ") ");
            System.out.println(temp.getName());
            i++;
        }
        Scanner userSelection = new Scanner(System.in);
        int studentID;
        while (true) {
            try {
                System.out.print("Select Student: ");
                studentID = Integer.parseInt(userSelection.nextLine());
                if (studentID < 1 || studentID > dataBase.getStudents().size()) {
                    throw new invalidOptionError();
                }
                break;
            } catch (invalidOptionError wrongMenuSelection) {
                System.out.println(wrongMenuSelection.getMessage());
            } catch (NumberFormatException notANumber) {
                System.out.println("Only enter menu item numbers.");
            }
        }
        return dataBase.getStudents().get(studentID - 1);
    }
}
