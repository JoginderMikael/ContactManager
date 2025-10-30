package git.joginder.mikael;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//CRUD DB OPERATIONS.
public class ContactDao {


    public boolean insertContact(Contact contact){
        String sql = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";

        try(Connection conn = DbUtil.getConnection()){
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, contact.getName());
                ps.setInt(2, contact.getPhone());
                ps.setString(3, contact.getEmail());

                ps.executeUpdate();
                return true;
            }else{
                IO.println("---------------------------------");
                IO.println("DB connection failed");
                IO.println("---------------------------------");

            }
        } catch (RuntimeException e) {
            IO.println("---------------------------------");
            IO.println("There was an error. " + e.getMessage());
            IO.println("---------------------------------");

            return false;
        } catch (SQLException e) {
            IO.println("---------------------------------");
            throw new RuntimeException("Failed in Inserting to DB." + e.getMessage());
        }

        return false;
    }

    public Contact findContactByName(String name){
        String sql = "SELECT name, phone, email FROM contacts WHERE name = ?";
        try(Connection conn = DbUtil.getConnection()){
            assert conn != null;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Contact contact = new Contact();
                    contact.setName(rs.getString("name"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getInt("phone"));
                    return contact;
                }
            }
        }catch (SQLException e){
            IO.println("---------------------------------");
            IO.println("Connection Error. " +e.getMessage());
            IO.println("---------------------------------");

        }
        return null;
    }

    List<Contact> findAll() {
        String sql = "SELECT id, name, phone, email FROM contacts";

        try(Connection conn = DbUtil.getConnection()){
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            try(ResultSet rs = ps.executeQuery()) {

                List<Contact> contacts = new ArrayList<>();

                while (rs.next()) {
                    Contact contact = new Contact();
                    contact.setId(rs.getInt("id"));
                    contact.setName(rs.getString("name"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getInt("phone"));
                    contacts.add(contact);
                }
                return contacts;
            }
        } catch (SQLException e) {
            IO.println("---------------------------------");
            IO.println("Error Occurred. " + e.getMessage());
            IO.println("---------------------------------");

        }
        return null;
    }

    public boolean update(int id, Contact newData){

        StringBuilder sql = new StringBuilder("UPDATE contacts SET ");
        List<Object> params = new ArrayList<>();

        //Building the SQL Update statement.
        if(newData.getName() != null && !newData.getName().isEmpty()){
            sql.append("name = ?, ");
            params.add(newData.getName());
        }

        if(newData.getEmail() != null && !newData.getEmail().isEmpty()){
            sql.append("email = ?, ");
            params.add(newData.getEmail());
        }

        if (newData.getPhone() != 0) {
            sql.append("phone = ?, ");
            params.add(newData.getPhone());
        }

        //removing the last comma and space
        if(params.isEmpty()){
            IO.println("---------------------------------");
            IO.println("No Fields to update");
            IO.println("---------------------------------");

            return false;
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        params.add(id);

        try(Connection conn = DbUtil.getConnection()) {
            assert conn != null;
            try(PreparedStatement ps = conn.prepareStatement(sql.toString())){

                for(int i = 0; i < params.size(); i++){
                    ps.setObject(i + 1, params.get(i));
                }

                int rowsUpdated = ps.executeUpdate();

                return rowsUpdated > 0;
            }
        } catch (Exception e){
            IO.println("---------------------------------");
            IO.println("Error Occurred. " + e.getMessage());
            IO.println("---------------------------------");

        }
        return true;
    }

    public boolean deleteContact(int phone){
        String sql = "DELETE FROM contacts WHERE phone::integer = ?";
        try(Connection conn = DbUtil.getConnection()){
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, phone);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            IO.println("---------------------------------");
            IO.println("ERROR " + e.getMessage());
            IO.println("---------------------------------");

        }
        return false;
    }

    public Contact findContactByEmail(String email) {
        String sql = "SELECT name, phone, email FROM contacts WHERE email = ?";
        try(Connection conn = DbUtil.getConnection()){
            assert conn != null;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Contact contact = new Contact();
                    contact.setName(rs.getString("name"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getInt("phone"));
                    return contact;
                }
            }
        }catch (SQLException e){
            IO.println("---------------------------------");
            IO.println("Connection Error. " +e.getMessage());
            IO.println("---------------------------------");

        }
        return null;
    }

    public Contact findContactById(int id) {
        String sql = "SELECT id, name, phone, email FROM contacts WHERE id::integer = ?";
        try(Connection conn = DbUtil.getConnection()){
            assert conn != null;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Contact contact = new Contact();
                    contact.setId((rs.getInt("id")));
                    contact.setName(rs.getString("name"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getInt("phone"));
                    return contact;
                }
            }
        }catch (SQLException e){
            IO.println("---------------------------------");
            IO.println("Connection Error. " +e.getMessage());
            IO.println("---------------------------------");

        }
        return null;
    }
}
