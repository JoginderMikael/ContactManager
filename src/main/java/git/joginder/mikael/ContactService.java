package git.joginder.mikael;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//All the contact service is handled here.
public class ContactService {

    private final ContactDao dao = new ContactDao();

    void createContact(Scanner scanner) {

        IO.println("ADD CONTACT.");

        Contact contact = new Contact();
        try {

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

            if (dao.insertContact(contact)) {
                IO.println("------------------------------------");
                IO.println("Contact details Added Successfully.");
                IO.println("Name: \t" + contact.getName() + "\n"
                        + "Email: \t" + contact.getEmail() + "\n"
                        + "Phone: \t" + contact.getPhone());
                IO.println("------------------------------------");
            } else {
                IO.println("---------------------------------");
                IO.println("Sorry, Failed. Try again.");
                IO.println("---------------------------------");
            }

        } catch (Exception e) {
            IO.println("---------------------------------");
            IO.println("ERROR WHILE ENTERING NUMBER." + e.getMessage());
            IO.println("---------------------------------");
            scanner.nextLine();
        }
    }

    public void getContact(Scanner scanner) {
        IO.println("VIEW CONTACT\n");
        int option = 0;
        try {
            IO.print("Search by (1. Name, 2. Email): ");
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            IO.println("WRONG ENTRY.");
            scanner.nextLine();
        }
        switch (option) {
            case 1 -> {
                IO.print("Enter the name: ");
                String name = scanner.nextLine();
                if (dao.findContactByName(name) != null) {
                    Contact contact = dao.findContactByName(name);
                    IO.println("---------------------");
                    IO.println("Name: \t" + contact.getName());
                    IO.println("Email: \t" + contact.getEmail());
                    IO.println("Phone: \t" + contact.getPhone());
                    IO.println("---------------------");
                } else {
                    IO.println("---------------------");
                    IO.println("CONTACT NOT FOUND");
                    IO.println("---------------------");
                }
            }
            case 2 -> {
                IO.print("Enter the Email: ");
                String email = scanner.nextLine();
                ContactDao contactDao = new ContactDao();
                if (contactDao.findContactByEmail(email) != null) {
                    Contact contact = contactDao.findContactByEmail(email);
                    IO.println("---------------------");
                    IO.println("Name: \t" + contact.getName());
                    IO.println("Email: \t" + contact.getEmail());
                    IO.println("Phone: \t" + contact.getPhone());
                    IO.println("---------------------");
                } else {
                    IO.println("---------------------");
                    IO.println("CONTACT NOT FOUND");
                    IO.println("---------------------");
                }
            }
            default -> {
                IO.println("---------------------------------");
                IO.println("WRONG OPTION");
                IO.println("---------------------------------");

            }

        }
    }

    List<Contact> getAllContacts() {
        ContactDao contactDao = new ContactDao();
       return contactDao.findAll();
    }

    public void updateContact(Scanner scanner){
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
                IO.println("---------------------------------");
                IO.println("Invalid number format. Skipping phone update.");
                IO.println("---------------------------------");
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

    public void deleteContact(Scanner scanner){
        IO.println("DELETE CONTACT");
        int phone = 0;
        try {
            IO.print("Enter the Phone: ");
            phone = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            IO.println("---------------------------------");
            IO.println("WRONG ENTRY!");
            IO.println("---------------------------------");
        }

        ContactDao contactDao = new ContactDao();
        if(contactDao.deleteContact(phone)){
            IO.println("---------------------------------");
            IO.println("Contact Deleted Successfully.");
            IO.println("---------------------------------");
        }else{
            IO.println("---------------------------------");
            IO.println("Failed");
            IO.println("---------------------------------");
        }
    }

    public void exportAllToJson(Path file) {

        List<Contact> list = getAllContacts();

        if(list.isEmpty()){
            IO.println("--------------------------------------");
            IO.println("Sorry, there is no contacts to export");
            IO.println("--------------------------------------");
        } else {
            JsonUtil.writeJsonToFile(list, file);
        }
    }

    List<Contact> importFromJson(Path file){

        List<Contact> resultList = new ArrayList<>();

        try{
            String content = Files.readString(file);
            List<Contact> list = JsonUtil.fromJsonList(content, Contact.class);

            for(Contact contact : list){
                //validation
                if(contact.getName() == null || contact.getName().isBlank()){
                    IO.println("---------------------------------");
                    IO.println("Skipping the contact with a missing name.");
                    IO.println("---------------------------------");
                }

               if(contact.getId() == 0){
                   dao.insertContact(contact);
                   resultList.add(contact);
                   IO.println("---------------------------------");
                   IO.println("Contact without ID added.");
                   IO.println("---------------------------------");
               }else{
                   Contact existing = dao.findContactById(contact.getId());
                   if(existing == null){
                       dao.insertContact(contact);
                       resultList.add(contact);
                       IO.println("---------------------------------");
                       IO.println("Inserted contact with ID: " + contact.getId());
                       IO.println("---------------------------------");
                   }else{
                       dao.update(contact.getId(), contact);
                       resultList.add(contact);
                       IO.println("---------------------------------");
                       IO.println("Updated Contacts with ID: " + contact.getId());
                       IO.println("---------------------------------");
                   }

               }
            }
        } catch (IOException e) {
            IO.println("---------------------------------");
            IO.println("Error Occurred." + e.getMessage());
            IO.println("---------------------------------");
        } catch (Exception e){
            IO.println("---------------------------------");
            IO.println("Error importing: " + e.getMessage());
            IO.println("---------------------------------");
        }
        return resultList;
    }
}
