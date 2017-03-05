package itmo_algs.week_1;

import org.junit.Rule;
import org.junit.rules.ExternalResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static util.FileUtils.readFileToString;
import static util.FileUtils.writeStringToFile;

/**
 * @author maksim-kiryanov
 */
public class FileBasedTest {
    private static final Path OUTPUT_FILE_PATH = Paths.get("output.txt");
    private static final Path INPUT_FILE_PATH = Paths.get("input.txt");

    @Rule
    public ExternalResource inputFileResource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            Files.createFile(INPUT_FILE_PATH);
        }

        @Override
        protected void after() {
            try {
                Files.deleteIfExists(INPUT_FILE_PATH);
            } catch (IOException e) {
                throw new RuntimeException("Could not delete file 'input.txt", e);
            }
        }
    };
    @Rule
    public ExternalResource outputFileResource = new ExternalResource() {
        @Override
        protected void after() {
            try {
                Files.deleteIfExists(OUTPUT_FILE_PATH);
            } catch (IOException e) {
                throw new RuntimeException("Could not delete file 'output.txt'", e);
            }
        }
    };

    void writeToInput(String content) {
        writeStringToFile(INPUT_FILE_PATH, content);
    }

    void assertOutputFileContent(String value) {
        assertThat("Invalid output file content", readFileToString(OUTPUT_FILE_PATH), is(value));
    }
}
