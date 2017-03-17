package itmo_algs.week_2;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class AntiQuickSortTest extends FileBasedTest {
    @Test
    public void test() {
        writeToInput("10");

        AntiQuickSort.main(new String[0]);

        assertOutputFileContent("6 7 8 9 10 5 4 3 2 1");
    }

    @Test
    public void test2() {
        writeToInput("3");

        AntiQuickSort.main(new String[0]);

        assertOutputFileContent("1 3 2");
    }
}