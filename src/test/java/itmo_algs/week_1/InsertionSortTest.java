package itmo_algs.week_1;

import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class InsertionSortTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("10\n1 8 4 2 3 7 5 6 9 0");

        InsertionSort.main(new String[0]);

        assertOutputFileContent("1 2 2 2 3 5 5 6 9 1\n0 1 2 3 4 5 6 7 8 9");
    }

    @Test
    public void testSameValuesVariant() {
        writeToInput("10\n1 1 1 1 1 1 1 1 1 1");

        InsertionSort.main(new String[0]);

        assertOutputFileContent("1 2 3 4 5 6 7 8 9 10\n1 1 1 1 1 1 1 1 1 1");
    }
}