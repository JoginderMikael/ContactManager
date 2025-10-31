package git.joginder.mikael;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//JSON Logic
public class JsonUtil {

    ContactService contactService = new ContactService();
    final Path path = Paths.get("temp/data");

    public void toJson(){
        IO.println("EXPORT TO JSON");

        //create the directory.
        Path fileDir = path.resolve("contacts.json");

        if(Files.exists(fileDir)){
            contactService.exportAllToJson(fileDir);
        }else{
            //create path
            try{
                Files.createDirectories(path);
                Files.createFile(fileDir);
                contactService.exportAllToJson(fileDir);
            } catch (Exception e){
                IO.println("---------------------------------");
                IO.println("Error Occurred. " + e.getMessage());
                IO.println("---------------------------------");
            }
        }
    }

    public static void writeJsonToFile(List<Contact> list, Path file){

        try{
            ObjectMapper mapper = new ObjectMapper();

            //pretty printing
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(file.toFile(), list);
            IO.println("----------------------------------");
            IO.println("Successfully written to JSON file.");
            IO.println("----------------------------------");
        } catch (IOException e){
            IO.println("---------------------------------");
            IO.println("Error writing to the JSON file: " + e.getMessage());
            IO.println("---------------------------------");

        }
    }

    public void fromJson(){
        Path fileDir = path.resolve("contacts.json");

        if(Files.exists(fileDir)){
            contactService.importFromJson(fileDir);

                        /*
                        for(Contact contact : contactService.importFromJson(fileDir)){
                            IO.println("---------------------");
                            IO.println("CONTACT ID: \t" +contact.getId());
                            IO.println("Name: \t" + contact.getName());
                            IO.println("Email: \t"+ contact.getEmail());
                            IO.println("Phone: \t"+ contact.getPhone());
                            IO.println("---------------------");
                        }
                         */
        } else {
            IO.println("---------------------------------");
            IO.println("The JSON file does not Exist.");
            IO.println("---------------------------------");
        }
        IO.println("---------------------------------");
        IO.println("IMPORT FROM JSON");
        IO.println("---------------------------------");
    }

    public static List<Contact> fromJsonList(String json, Class<Contact> clazz){
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            JavaType listOfContacts = typeFactory.constructCollectionType(List.class, clazz);

            return objectMapper.readValue(json, listOfContacts);

        } catch (IOException e) {
            IO.println("---------------------------------");
            IO.println("Error Occurred." + e.getMessage());
            IO.println("---------------------------------");
            return new ArrayList<>();
        }
    }

}
