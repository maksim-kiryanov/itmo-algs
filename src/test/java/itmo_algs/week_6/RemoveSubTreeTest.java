package itmo_algs.week_6;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class RemoveSubTreeTest extends FileBasedTest {
    @Test
    public void test() {
        writeToInput("6\n" +
                "-2 0 2\n" +
                "8 4 3\n" +
                "9 0 0\n" +
                "3 6 5\n" +
                "6 0 0\n" +
                "0 0 0\n" +
                "4\n" +
                "6 9 7 8");

        RemoveSubTree.main(new String[0]);

        assertOutputFileContent("5\n" +
                "4\n" +
                "4\n" +
                "1");
    }
}