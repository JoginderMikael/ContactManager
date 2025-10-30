package git.joginder.mikael;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

//All the contact service is handled here.
public class ContactService {

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
        return null;
    }
}
