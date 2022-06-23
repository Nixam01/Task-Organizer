package aproproject.Utilities;

import aproproject.Tasks.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
    private static final ObjectMapper mapper = new ObjectMapper();


    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Method which writes a given list object to the given file path
     * @param tasks , is the list object containing the task we want to write
     * @param path , is the String which dictates the path of the file to want o write to
     */
    public static void writeToFile(List<Task> tasks, String path) throws IOException {
        if (validateString(path)) {
            mapper.writeValue(new File(path), tasks);
        }
    }

    /**
     *Method which reads list objects from JSON file from a given file path
     * @param path , is the String which dictates the path of the file to want to read from
     * @return , returns object list contents
     */

    public static List<Task> readFromFile(String path) throws IOException {
        List<Task> value = null;
        JSONValidator jV = new JSONValidator();
        if (!path.split("\\.")[1].equals("json") || !jV.isJSONFileValid("schema.json", path)){
            return null;
        }
        value = new ArrayList<>();
            value = mapper.readValue(new File(path), new TypeReference<ArrayList<Task>>() {
            });

        return value;
    }

    /**
     * Method which writes a given list object to the given file path in a readable file format
     * @param tasks , is the list object containing the task we want to write
     * @param path , is the String which dictates the path of the file to want o write to
     */
    public static void writeToReadableFile(List<Task> tasks, String path) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path))) {
            for (Task t : tasks) {
                fileWriter.write(t.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if string is alphanumeric with spaces, dashes and underscores.
     *
     * @param string string to validate
     * @return logical value describing correctness in the given string
     */
    public static boolean validateString(String string) {
        String pattern = "[a-zA-Z0-9\\-_ąćęłńóśźżĄĆĘŁŃÓŚŹŻ. ]+";
        return !string.isBlank() && string.matches(pattern);
    }
}