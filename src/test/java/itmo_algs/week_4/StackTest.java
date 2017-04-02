package itmo_algs.week_4;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class StackTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("6\n" +
                "+ 1\n" +
                "+ 10\n" +
                "-\n" +
                "+ 2\n" +
                "+ 1234\n" +
                "-");

        Stack.main(new String[0]);

        assertOutputFileContent("10\n" +
                "1234");
    }
}