package git.joginder.mikael;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
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
            IO.println("Error writing to the JSON file: " + e.getMessage());
        }
    }

    public Contact fromJson(){
        return null;
    }

    public List<Object> fromJsonList(){
        return null;
    }

}
