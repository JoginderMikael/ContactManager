package git.joginder.mikael;

//Set and Get contact details
public class Contact {

   private String name;
   private String email;
   private int phone;
   private int id;

    public Contact(){

    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPhone(int phone){
        this.phone = phone;
    }

    public int getPhone(){
        return this.phone;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
