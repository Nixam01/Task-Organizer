package aproproject.Utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JSONValidatorTest {

    @Test
    void isJSONFileValidTest() throws IOException {
        JSONValidator validator = new JSONValidator();
        assertTrue(validator.isJSONFileValid("src/main/resources/schema.json", "data.json"));
        assertFalse(validator.isJSONFileValid("src/main/resources/schema.json", "src/main/resources/data_2.json"));
        assertTrue(validator.isJSONFileValid("src/main/resources/schema.json", "src/main/resources/data_3.json"));
    }
}