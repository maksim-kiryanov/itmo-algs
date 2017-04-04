package itmo_algs.week_5;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class PriorityQueueTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("8\n" +
                "A 3\n" +
                "A 4\n" +
                "A 2\n" +
                "X\n" +
                "D 2 1\n" +
                "X\n" +
                "X\n" +
                "X");

        PriorityQueue.main(new String[0]);

        assertOutputFileContent("2\n" +
                "1\n" +
                "3\n" +
                "*");
    }
}