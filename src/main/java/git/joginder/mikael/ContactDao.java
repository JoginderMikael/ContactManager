package git.joginder.mikael;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

//CRUD DB OPERATIONS.
public class ContactDao {

    public boolean insertContact(Contact contact){
        String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";

        Connection conn = null;

        try{
            conn = DbUtil.getConnection();
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, contact.getName());
                ps.setInt(2, contact.getPhone());
                ps.setString(3, contact.getEmail());

                ps.executeUpdate();
                return true;
            }else{
                IO.println("DB connection failed");
            }
        } catch (RuntimeException e) {
            IO.println("There was an error. " + e.getMessage());
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Failed in Inserting to DB." + e.getMessage());
        }

        return false;
    }

    public void findContact(){

    }

    List<Contact> findAll(){
        return null;
    }

    boolean update(Contact contact){
        return true;
    }

    boolean deleteContact(){
        return true;
    }
}
