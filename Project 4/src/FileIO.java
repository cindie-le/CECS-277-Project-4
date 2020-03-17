import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Cindie Le, Alexander Pham, Peter Vu
 * 3/15/2020
 * This program writes into a file full of sales data that the user inputs in and reads that file, separating all of the
 * different services into their own categorized files.
 * Inputs: The sales data of the three services: Dinner, Conference, Lodging in the form Name;Service;Price;Date
 * Output: The .txt files of all the sale services and each of the different services in their own files
 */
public class FileIO {
    public static void main(String[] args) {
        while(true) {
            Scanner in = new Scanner(System.in);
            displayParts();
            // Get and validate input
            int userInput = -1;
            while (userInput < 1 || userInput > 3) {
                System.out.print("What would you like do? : ");
                userInput = in.nextInt();
            }
            // Prompt for data to write to sales file
            if (userInput == 1) {
                try {
                    PrintWriter writer = new PrintWriter("sales.txt");
                    //Initialize variables
                    String name, service, date, user_input;
                    double sale;
                    boolean loop = true;

                    System.out.println("Please enter sales information about the client. ");
                    //Ask for user input for all the fields and write to the file
                    while (loop) {

                        //Get name
                        System.out.print("First & Last Name: ");
                        name = getString();


                        //Get service
                        System.out.print("Service: ");
                        service = getString();

                        //Get/validate sale
                        System.out.print("Amount of Sale: $");
                        sale = getDouble();

                        //Get date
                        System.out.print("Date (MM/DD/YYYY): ");
                        date = getString();
                        while (!date.contains("/")) {
                            System.out.print("Please enter in the form MM/DD/YYYY: ");
                            date = getString();
                        }

                        //Write the information to the file
                        writer.println(name + ";" + service + ";" + String.format("%.2f", sale) + ";" + date);

                        //Ask the user if they wanted to input another client's sales information
                        System.out.print("Would you like to enter information about another client? (Y/N): ");
                        user_input = in.next();
                        System.out.println(user_input);
                        if (user_input.equalsIgnoreCase("N")) {
                            loop = false;
                        }
                    }
                    // Close files
                    writer.close();
                // Catch any thrown exceptions
                } catch (FileNotFoundException fnf) {
                    System.out.println("File was not found.");
                }
            // Reads sales file and separates each service
            } else if (userInput == 2){
                try {
                    Scanner read = new Scanner(new File(args[0]));
                    // Write each of the three services into their own dedicated files
                    PrintWriter dinnerWriter = new PrintWriter("Dinner.txt");
                    PrintWriter conferenceWriter = new PrintWriter("Conference.txt");
                    PrintWriter lodgingWriter = new PrintWriter("Lodging.txt");
                    do {
                        String line = read.nextLine();
                        // Looks for dinner, conference, or lodging
                        String[] lineSegments = line.split(";");

                        // Writes to dinner file
                        if (lineSegments[1].equals("Dinner")) {
                            dinnerWriter.println(line);
                        }
                        // Writes to conference file
                        else if (lineSegments[1].equals("Conference")) {
                            conferenceWriter.println(line);
                        }
                        // Writes to Lodging file
                        else if (lineSegments[1].equals("Lodging")) {
                            lodgingWriter.println(line);
                        // Throw exception when an unknown service is found
                        } else {
                            throw new UnknownTransactionException("Service does not exist");
                        }

                    } while (read.hasNext());
                    System.out.println("Sales have been separated into files");
                    // Close files
                    read.close();
                    dinnerWriter.close();
                    conferenceWriter.close();
                    lodgingWriter.close();
                // Catch any thrown exceptions
                } catch (FileNotFoundException fnf) {
                    System.out.println("File not found");
                } catch (UnknownTransactionException ut) {
                    System.out.println("Unknown service has been found");
                }
            }
            else {
                System.out.println("Goodbye");
                break;
            }
        }

    }

    /**
     * Gets and validates user input in the form of a string
     * @return s string
     */
    public static String getString() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while (s.length() <= 0)  {
            System.out.print("Invalid. Enter a string: ");
            s = in.nextLine();
        }
        return s;
    }

    /**
     * Gets and validates user input in the form of a positive double
     * @return d double
     */
    public static double getDouble() {
        Scanner in = new Scanner(System.in);
        double d;
        while(true) {
            if(in.hasNextDouble()) {
                d = in.nextDouble();
                if (d <= 0) {
                    System.out.print("Invalid. Please enter a positive number: ");
                    continue;
                }
                break;
            } else {
                in.next();
                System.out.print("Invalid. Please enter a number: ");
            }
        }
        return d;
    }

    /**
     * Displays the menu to the user
     */
    public static void displayParts(){
        System.out.println( "1. Enter new sale\n" +
                            "2. Organize sales\n" +
                            "3. Quit");
    }
}
