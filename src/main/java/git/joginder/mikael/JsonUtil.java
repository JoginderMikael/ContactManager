package git.joginder.mikael;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

//JSON Logic
public class JsonUtil {

    public String toJson(){
        return null;
    }

    public static void writeJsonToFile(List<Contact> list, Path file){

        try{
            ObjectMapper mapper = new ObjectMapper();

            //pretty printing
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(file.toFile(), list);
            IO.println("----------------------------------");
            IO.println("Sucessfully written to JSON file.");
            IO.println("----------------------------------");
        } catch (IOException e){
            IO.println("---------------------------------");
            IO.println("Error writing to the JSON file: " + e.getMessage());
            IO.println("---------------------------------");

        }
    }

    public Contact fromJson(){
        return null;
    }

    public static List<Contact> fromJsonList(String json, Class<Contact> clazz){
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            JavaType listOfContacts = typeFactory.constructCollectionType(List.class, clazz);

            return objectMapper.readValue(json, listOfContacts);

        } catch (IOException e) {
            IO.println("---------------------------------");
            IO.println("Error Occured." + e.getMessage());
            IO.println("---------------------------------");
            return new ArrayList<>();
        }
    }

}
