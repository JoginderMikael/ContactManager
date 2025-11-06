import git.joginder.mikael.Contact;
import git.joginder.mikael.ContactDao;
import git.joginder.mikael.DbUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ContactDaoTest {

    private ContactDao contactDao;
    @BeforeEach
            void setup(){
        contactDao = new ContactDao();
    }

    @Test
    public void ContactUpdateTest(){
        //arrange
    Contact contact = new Contact();
    contact.setEmail("Joginderi45@gmail.com");
    contact.setPhone(98499384);
    contact.setName("Joginder MK1");

    //execute
    boolean result = contactDao.insertContact(contact);

    //assert
        assertTrue(result, "insertContact() should return true when insert succeeds");

        //verify the record is in the DB
        try(Connection conn = DbUtil.getConnection()) {
            assertNotNull(conn);
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM contacts WHERE email = ?")){
                ps.setString(1, "Joginderi45@gmail.com");
                ResultSet rs = ps.executeQuery();

                assertTrue(rs.next(), "Inserted contact exist in database");
                assertEquals("Joginder MK1", rs.getString("name"));
                assertEquals(98499384, rs.getInt("phone"));
                assertEquals("Joginderi45@gmail.com", rs.getString("email"));
            }
        } catch(SQLException e){
            fail("Database query failed: " + e.getMessage());
        }


}
    @AfterEach
    void tearDown(){
        //cleaning up the inserted data
        try(Connection conn = DbUtil.getConnection()) {
            assertNotNull(conn);
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM contacts WHERE email = ?")){
                ps.setString(1, "Joginderi45@gmail.com");
                ps.executeQuery();

            }
        } catch (SQLException e){
            IO.println("Clean up failed: " + e.getMessage());
        }
    }

}
