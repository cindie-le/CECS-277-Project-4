import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {
    public static void main(String[] args) {
        while(true) {
            Scanner in = new Scanner(System.in);
            displayParts();
            int userInput = -1;
            while (userInput < 1 || userInput > 3) {
                System.out.print("What would you like do? : ");
                userInput = in.nextInt();
            }
            if (userInput == 1) {
                try {
                    PrintWriter writer = new PrintWriter("sales.txt");

                    //Initialize variables
                    String name = "";
                    String service = "";
                    double sale = 0.0;
                    String date = "";
                    String user_input = "";
                    System.out.println("Please enter sales information about the client. ");

                    //Ask for user input for all the fields and write to the file
                    while (true) {

                        //Get name
                        System.out.print("First & Last Name: ");
                        name = getString(name);


                        //Get service
                        System.out.print("Service: ");
                        service = getString(service);

                        //Get/validate sale
                        System.out.print("Amount of Sale: $");
                        sale = getDouble(sale);


                        //Consume the next newline character that is leftover from reading in the double value for sale.
                        //String consume = in.nextLine();

                        //Get date
                        System.out.print("Date (MM/DD/YYYY): ");
                        date = getString(date);


                        //Write the information to the file
                        writer.println(name + ";" + service + ";" + String.format("%.2f", sale) + ";" + date);

                        //Ask the user if they wanted to input another client's sales information
                        System.out.print("Would you like to enter information about another client? (Y/N):");
                        user_input = in.next();
                        System.out.println(user_input);
                        if (user_input.equals("N")) {
                            break;
                        }
                    }
                    writer.close();

                } catch (FileNotFoundException fnf) {
                    System.out.println("File was not found.");
                }
            }
            if (userInput == 2){
                try {
                    Scanner read = new Scanner(new File("sales.txt"));
                    PrintWriter dinnerWriter = new PrintWriter("Dinner.txt");
                    PrintWriter conferenceWriter = new PrintWriter("Conference.txt");
                    PrintWriter lodgingWriter = new PrintWriter("Lodging.txt");
                    do {
                        String line = read.nextLine();
                        //Looks for dinner, conference, or lodging
                        String[] lineSegments = line.split(";");
                        //Writes to dinner file
                        if (lineSegments[1].equals("Dinner")) {
                            dinnerWriter.println(line);
                        }
                        //Writes to conference file
                        if (lineSegments[1].equals("Conference")) {
                            conferenceWriter.println(line);
                        }
                        //Writes to Lodging file
                        if (lineSegments[1].equals("Lodging")) {
                            lodgingWriter.println(line);
                        }
                    } while (read.hasNext());
                    read.close();
                    dinnerWriter.close();
                    conferenceWriter.close();
                    lodgingWriter.close();
                } catch (FileNotFoundException fnf) {
                    System.out.println("File not found");
                }
                System.out.println("Sales have been separated into files");
            }
            else{
                System.out.println("Later loser");
                break;
            }
        }

    }

    /**
     * Gets and validates user input in the form of a string
     * @param s string
     * @return s string
     */
    public static String getString (String s) {
        Scanner in = new Scanner(System.in);
        s = in.nextLine();
        while (s.length() <= 0)  {
            System.out.println("Invalid. Enter " + s + " correctly: ");
            s = in.nextLine();
        }
        return s;
    }

    /**
     * Gets and validates user input in the form of a double
     * @param d double
     * @return d double
     */
    public static double getDouble ( Double d ) {
        Scanner in = new Scanner(System.in);
        while( true ) {
            if( in.hasNextDouble() ) {
                d = in.nextDouble();
                break;
            } else {
                in.next();
                System.out.println( "Invalid. Please enter a number." );
            }
        }
        return d;
    }

    public static void displayParts(){
        System.out.println( "1. Enter new sale\n" +
                            "2. Organize sales\n" +
                            "3. Quit");
    }
}
