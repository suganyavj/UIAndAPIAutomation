package testDataFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TestDataLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static TesData loadTestData() throws IOException {
        File file = new File("src/test/resources/TestData/testdata.json");
        return objectMapper.readValue(file, TesData.class);
    }

}
