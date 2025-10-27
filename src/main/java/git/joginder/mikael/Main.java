package git.joginder.mikael;

import java.util.Scanner;

public class Main {

    void main(){

        Scanner scanner = new Scanner(System.in);

        IO.println("**********************************");
        IO.println("WELCOME TO PHONE BOOK.");
        IO.println("**********************************");

        boolean notExit = true;
        while(notExit){
            IO.println(
                    """
                            ADD, VIEW, UPDATE, & DELETE CONTACTS\
                            
                            1. ADD Contact. \
                            
                            2. VIEW Contact. \
                            
                            3. VIEW All Contacts. \
                            
                            4. UPDATE Contact. \
                            
                            5. DELETE Contact. \
                            
                            6. EXPORT to JSON. \
                            
                            7. IMPORT from JSON. \
                            
                            8. EXIT"""

            );

            IO.print("\nCHOOSE AN OPTION: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1 -> IO.println("You have chosen to Add a contact.");
                case 2 -> IO.println("You have chosen to View a contact.");
                case 3 -> IO.println("You have chosen to VIEW ALL contact.");
                case 4 -> IO.println("You have chosen to Update a contact");
                case 5 -> IO.println("You have chosen to Delete a contact");
                case 6 -> IO.println("You have chosen to EXPORT TO JSON");
                case 7 -> IO.println("You have chosen to IMPORT FROM");
                case 8 -> {
                    IO.println("--------------------------");
                    IO.println("Thanks for using the APP.");
                    IO.println("--------------------------");
                    notExit = false;
                }
                default -> IO.println("WRONG ENTRY");
            }

        }
    scanner.close();
    }

}
