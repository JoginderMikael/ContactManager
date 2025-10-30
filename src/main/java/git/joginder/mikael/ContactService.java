package git.joginder.mikael;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//All the contact service is handled here.
public class ContactService {

    private final ContactDao dao = new ContactDao();

    Contact createContact(){
        return null;
    }

    Contact getContact(int id){
        return null;
    }

    List<Contact> getAllContacts() throws SQLException {
        ContactDao contactDao = new ContactDao();
       return contactDao.findAll();
    }

    Contact upateContact(){
        return null;
    }

    boolean deleteContact(int id){
        return false;
    }

    public void exportAllToJson(Path file) throws SQLException {

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
                    IO.println("Skipping the contact with a missing name.");
                }

               if(contact.getId() == 0){
                   dao.insertContact(contact);
                   resultList.add(contact);
                   IO.println("Contact without ID added.");
               }else{
                   Contact existing = dao.findContactById(contact.getId());
                   if(existing == null){
                       dao.insertContact(contact);
                       resultList.add(contact);
                       IO.println("Inserted contact with ID: " + contact.getId());
                   }else{
                       dao.update(contact.getId(), contact);
                       resultList.add(contact);
                       IO.println("Updated Contacts with ID: " + contact.getId());
                   }

               }
            }
        } catch (IOException e) {
            IO.println("Error Occured." + e.getMessage());
        } catch (Exception e){
            IO.println("Error importing: " + e.getMessage());
        }
        return resultList;
    }
}
