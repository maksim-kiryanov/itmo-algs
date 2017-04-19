package itmo_algs.week_6;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class BinarySearchTest extends FileBasedTest {
    @Test
    public void test() {
        writeToInput("5\n" +
                "1 1 2 2 2\n" +
                "3\n" +
                "1 2 3");

        BinarySearch.main(new String[0]);

        assertOutputFileContent("1 2\n" +
                "3 5\n" +
                "-1 -1");
    }

    @Test
    public void test2() {
        writeToInput("7\n" +
                "1 1 2 2 2 2 4\n" +
                "3\n" +
                "1 2 3");

        BinarySearch.main(new String[0]);

        assertOutputFileContent("1 2\n" +
                "3 6\n" +
                "-1 -1");
    }
}