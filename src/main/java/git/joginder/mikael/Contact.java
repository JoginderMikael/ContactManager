package git.joginder.mikael;

//Set and Get contact details
public class Contact {

   private String name;
   private String email;
   private int phone;

    Contact(){

    }

    Contact(String name, int phone){
        this.name = name;
        this.phone = phone;
    }
    Contact(String name, int phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
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
}
