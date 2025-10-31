package git.joginder.mikael;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    void main() {
        Scanner scanner = new Scanner(System.in);
        ContactService contactService = new ContactService();
        JsonUtil jsonUtil = new JsonUtil();

        IO.println("*************************");
        IO.println("WELCOME TO PHONE BOOK.");
        IO.println("*************************");

        boolean notExit = true;
        while(notExit){
            IO.println(
                    """
                            MENU\
                            
                            1. ADD Contact. \
                            
                            2. VIEW Contact. \
                            
                            3. VIEW All Contacts. \
                            
                            4. UPDATE Contact. \
                            
                            5. DELETE Contact. \
                            
                            6. EXPORT to JSON. \
                            
                            7. IMPORT from JSON. \
                            
                            8. EXIT"""

            );

                int choice;
            try {
                IO.print("\nCHOOSE AN OPTION: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch(InputMismatchException e){
                IO.println("------------------------------------------------");
                IO.println("INVALID CHOICE. PLEASE ENTER VALID CHOICE (1-8)");
                IO.println("------------------------------------------------");
                scanner.nextLine();
                continue;
            }
            switch(choice){
                case 1 -> contactService.createContact(scanner);
                case 2 -> contactService.getContact(scanner);
                case 3 -> contactService.getAllContacts();
                case 4 -> contactService.updateContact(scanner);
                case 5 -> contactService.deleteContact(scanner);
                case 6 -> jsonUtil.toJson();
                case 7 -> jsonUtil.fromJson();
                case 8 -> {
                    IO.println("--------------------------");
                    IO.println("Thanks for using the APP.");
                    IO.println("--------------------------");
                    notExit = false;
                }
                default -> {
                    IO.println("---------------------------------");
                    IO.println("WRONG ENTRY. PLEASE CHOOSE (1-8).");
                    IO.println("---------------------------------");
                }
            }

        }
    scanner.close();
    }


}
