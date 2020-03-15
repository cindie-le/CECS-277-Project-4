import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

//Part 1
public class Part1 {
    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter("sales.txt");
            Scanner in = new Scanner(System.in);

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
                writer.println(name + ";" + service + ";" +  String.format("%.2f", sale) + ";" + date);

                //Ask the user if they wanted to input another client's sales information
                System.out.print("Would you like to enter information about another client? (Y/N):");
                user_input = in.nextLine();
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
}


