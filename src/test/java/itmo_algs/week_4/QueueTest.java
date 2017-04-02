package itmo_algs.week_4;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class QueueTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("4\n" +
                "+ 1\n" +
                "+ 10\n" +
                "-\n" +
                "-");

        Queue.main(new String[0]);

        assertOutputFileContent("1\n" +
                "10");
    }

}