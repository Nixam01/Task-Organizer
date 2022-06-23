package aproproject.Utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class JSONValidator {

    private ObjectMapper mapper = new ObjectMapper();

    public JSONValidator() {
    }

    protected JsonNode getJsonNodeFromClasspath(String name) throws IOException {
        InputStream is1 = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(name);
        return mapper.readTree(is1);
    }

    protected JsonNode getJsonNodeFromStringContent(String content) throws IOException {
        return mapper.readTree(content);
    }

    protected JsonNode getJsonNodeFromUri(String uri) throws IOException {
        return mapper.readTree(new URL(uri));
    }

    protected JsonSchema getJsonSchemaFromClasspath(String name) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(name);
        return factory.getSchema(is);
    }

    protected JsonSchema getJsonSchemaFromStringContent(String schemaContent) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        return factory.getSchema(schemaContent);
    }

    protected JsonSchema getJsonSchemaFromUri(String uri) throws URISyntaxException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        return factory.getSchema(new URI(uri));
    }

    protected JsonSchema getJsonSchemaFromJsonNode(JsonNode jsonNode) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        return factory.getSchema(jsonNode);
    }

    // Automatically detect version for given JsonNode
    protected JsonSchema getJsonSchemaFromJsonNodeAutomaticVersion(JsonNode jsonNode) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(jsonNode));
        return factory.getSchema(jsonNode);
    }

    protected boolean isJSONFileValid(String schemaPath, String dataPath) throws IOException {
            String nodeString = Files.readString(Paths.get(dataPath));
            String schemaString = Files.readString(Paths.get(schemaPath));
            JsonNode schemaNode = getJsonNodeFromStringContent("{\"$schema\": \"http://json-schema.org/draft-06/schema#\"," + schemaString.substring(3));
            JsonSchema schema = getJsonSchemaFromJsonNodeAutomaticVersion(schemaNode);
            JsonNode node = getJsonNodeFromStringContent(nodeString);
            Set<ValidationMessage> errors = schema.validate(node);
        for (ValidationMessage error : errors) {
            System.out.println(error.toString());
        }
            //System.out.println(errors.size());

            return !(errors.size() > 0);
    }
}
