package git.joginder.mikael;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
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

                int choice = 0;
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
                case 1 -> {
                    IO.println("ADD CONTACT.");

                    Contact contact = new Contact();
                    ContactDao contactDao = new ContactDao();

                    String name;
                    String email;
                    int phone;

                    IO.print("Enter Name: ");
                    name = scanner.nextLine();

                    IO.print("Enter Number: ");
                    phone = scanner.nextInt();
                    scanner.nextLine();

                    IO.print("Enter Email: ");
                    email = scanner.nextLine();


                    contact.setName(name);
                    contact.setPhone(phone);
                    contact.setEmail(email);

                    if(contactDao.insertContact(contact)){
                        IO.println("------------------------------------");
                        IO.println("Contact details Added Successfully.");
                        IO.println("Name: \t" + contact.getName() + "\n"
                                + "Email: \t" + contact.getEmail() + "\n"
                                + "Phone: \t" + contact.getPhone());
                        IO.println("------------------------------------");
                    }else{
                        IO.println("Sorry, Failed. Try again.");
                    }
                }
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
