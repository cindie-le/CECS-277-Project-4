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
                name = in.nextLine();

                //Get service
                System.out.print("Service: ");
                service = in.nextLine();

                //Get/validate sale
                System.out.print("Amount of Sale: $");

                while( true ) {
                    if( in.hasNextDouble() ) {
                        sale = in.nextDouble();
                        break;
                    } else {
                        in.next();
                        System.out.println( "Invalid. Please enter a number." );
                    }
                }

                //Consume the next newline character that is leftover from reading in the double value for sale.
                String consume = in.nextLine();

                //Get date
                System.out.print("Date (MM/DD/YYYY): ");
                date = in.nextLine();
                System.out.println("The date is " + date);

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
}


