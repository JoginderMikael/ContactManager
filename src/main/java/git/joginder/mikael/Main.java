package git.joginder.mikael;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    void main() {
        Scanner scanner = new Scanner(System.in);
        ContactService contactService = new ContactService();

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

            final Path path = Paths.get("temp/data");

            switch(choice){
                case 1 -> contactService.createContact(scanner);
                case 2 -> contactService.getContact(scanner);
                case 3 -> {
                    IO.println("ALL CONTACTS");
                    for(Contact contact : contactService.getAllContacts()){
                        IO.println("---------------------");
                        IO.println("CONTACT ID: \t" +contact.getId());
                        IO.println("Name: \t" + contact.getName());
                        IO.println("Email: \t"+ contact.getEmail());
                        IO.println("Phone: \t"+ contact.getPhone());
                        IO.println("---------------------");
                    }

                }
                case 4 -> contactService.updateContact(scanner);
                case 5 -> contactService.deleteContact(scanner);
                case 6 -> {
                    IO.println("EXPORT TO JSON");

                    //create the directory.
                    Path fileDir = path.resolve("contacts.json");

                    if(Files.exists(fileDir)){
                        contactService.exportAllToJson(fileDir);
                    }else{
                        //create path
                        try{
                            Files.createDirectories(path);
                            Files.createFile(fileDir);
                            contactService.exportAllToJson(fileDir);
                        } catch (Exception e){
                            IO.println("---------------------------------");
                            IO.println("Error Occurred. " + e.getMessage());
                            IO.println("---------------------------------");
                        }
                    }

                }
                case 7 -> {
                    Path fileDir = path.resolve("contacts.json");

                    if(Files.exists(fileDir)){

                        for(Contact contact : contactService.importFromJson(fileDir)){
                            IO.println("---------------------");
                            IO.println("CONTACT ID: \t" +contact.getId());
                            IO.println("Name: \t" + contact.getName());
                            IO.println("Email: \t"+ contact.getEmail());
                            IO.println("Phone: \t"+ contact.getPhone());
                            IO.println("---------------------");
                        }
                    } else {
                        IO.println("---------------------------------");
                        IO.println("The JSON file does not Exist.");
                        IO.println("---------------------------------");
                    }
                    IO.println("---------------------------------");
                    IO.println("IMPORT FROM JSON");
                    IO.println("---------------------------------");

                }
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
