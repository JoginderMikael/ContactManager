package git.joginder.mikael;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    void main() throws SQLException {
        Scanner scanner = new Scanner(System.in);

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
                case 2 -> {
                    IO.println("VIEW CONTACT\n");
                    int option = 0;
                    try {
                        IO.print("Search by (1. Name, 2. Email): ");
                         option = scanner.nextInt();
                        scanner.nextLine();
                    }catch(InputMismatchException e){
                        IO.println("WRONG ENTRY.");
                        scanner.nextLine();
                        continue;
                    }
                    switch (option){
                        case 1 -> {
                            IO.print("Enter the name: ");
                            String name = scanner.nextLine();
                            ContactDao contactDao = new ContactDao();
                            if(contactDao.findContactByName(name) != null){
                                Contact contact = contactDao.findContactByName(name);
                                IO.println("---------------------");
                                IO.println("Name: \t" + contact.getName());
                                IO.println("Email: \t"+ contact.getEmail());
                                IO.println("Phone: \t"+ contact.getPhone());
                                IO.println("---------------------");
                            }else{
                                IO.println("---------------------");
                                IO.println("CONTACT NOT FOUND");
                                IO.println("---------------------");
                            }
                        }
                        case 2 -> {
                            IO.print("Enter the Email: ");
                            String email = scanner.nextLine();
                            ContactDao contactDao = new ContactDao();
                            if(contactDao.findContactByEmail(email) != null){
                                Contact contact = contactDao.findContactByEmail(email);
                                IO.println("---------------------");
                                IO.println("Name: \t" + contact.getName());
                                IO.println("Email: \t"+ contact.getEmail());
                                IO.println("Phone: \t"+ contact.getPhone());
                                IO.println("---------------------");
                            }else{
                                IO.println("---------------------");
                                IO.println("CONTACT NOT FOUND");
                                IO.println("---------------------");
                            }
                        }
                        default -> IO.println("WRONG OPTION");

                    }

                }
                case 3 -> {

                    IO.println("ALL CONTACTS");
                    ContactDao contactDao = new ContactDao();
                    for(Contact contact : contactDao.findAll()){
                        IO.println("---------------------");
                        IO.println("CONTACT ID: \t" +contact.getId());
                        IO.println("Name: \t" + contact.getName());
                        IO.println("Email: \t"+ contact.getEmail());
                        IO.println("Phone: \t"+ contact.getPhone());
                        IO.println("---------------------");
                    }

                }
                case 4 -> {

                    IO.println("UPDATE CONTACT");
                    IO.print("Enter Contact ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    ContactDao contactDao = new ContactDao();
                    Contact contact = new Contact();

                    IO.print("Enter NEW NAME (press enter to skip): ");
                    String name = scanner.nextLine();
                    if (!name.isBlank()) contact.setName(name);

                    IO.print("Enter NEW EMAIL(press enter to skip): ");
                    String email = scanner.nextLine();
                    if (!email.isBlank()) contact.setEmail(email);

                    IO.print("Enter NEW NUMBER (press enter to skip): ");
                    String phoneInput = scanner.nextLine();

                    if (!phoneInput.isBlank()) {
                        try {
                            contact.setPhone(Integer.parseInt(phoneInput));
                        } catch (NumberFormatException e) {
                            IO.println("Invalid number format. Skipping phone update.");
                        }
                    }

                    if(contactDao.update(id, contact)){
                        IO.println("---------------");
                        IO.println("UPDATE COMPLETE");
                        IO.println("---------------");
                    } else {
                        IO.println("---------------");
                        IO.println("UPDATE FAILED");
                        IO.println("---------------");
                    }
                }
                case 5 -> {
                    IO.println("DELETE CONTACT");
                    int phone = 0;
                    try {
                        IO.print("Enter the Phone: ");
                        phone = scanner.nextInt();
                        scanner.nextLine();
                    }catch (InputMismatchException e){
                        IO.println("WRONG ENTRY!");
                    }

                    ContactDao contactDao = new ContactDao();
                    if(contactDao.deleteContact(phone)){
                        IO.println("Contact Deleted Successfully.");
                    }else{
                        IO.println("Failed");
                    }
                }
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
