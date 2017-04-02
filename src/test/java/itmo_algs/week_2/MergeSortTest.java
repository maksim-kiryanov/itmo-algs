package itmo_algs.week_2;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class MergeSortTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("10\n1 8 2 1 4 7 3 2 3 6");

        MergeSort.main(new String[0]);

        assertOutputFileContent("1 2 1 8\n" +
                "3 4 1 2\n" +
                "5 6 4 7\n" +
                "7 8 2 3\n" +
                "9 10 3 6\n" +
                "1 4 1 8\n" +
                "5 8 2 7\n" +
                "1 8 1 8\n" +
                "1 10 1 8\n" +
                "1 1 2 2 3 3 4 6 7 8");
    }

    @Test
    public void testOneElementArray() {
        writeToInput("1\n1");

        MergeSort.main(new String[0]);

        assertOutputFileContent("1");
    }
}