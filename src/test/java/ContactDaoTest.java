import git.joginder.mikael.Contact;
import git.joginder.mikael.ContactDao;
import org.junit.jupiter.api.Test;

public class ContactDaoTest {

@Test
    public void test(){

    ContactDao contactDao = new ContactDao();
    Contact contact = new Contact();
    contact.setEmail("Joginderi@gmail.com");
    contact.setPhone(98499382);
    contact.setName("Joginder MK");

    contactDao.insertContact(contact);

    assert true;
}

}
