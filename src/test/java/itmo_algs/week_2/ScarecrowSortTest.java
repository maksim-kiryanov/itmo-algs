package itmo_algs.week_2;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class ScarecrowSortTest extends FileBasedTest {
    @Test
    public void testNegativeVariant() {
        writeToInput("3 2\n" +
                "2 1 3");

        ScarecrowSort.main(new String[0]);

        assertOutputFileContent("NO");
    }

    @Test
    public void testPositiveVariant() {
        writeToInput("5 3\n" +
                "1 5 3 4 1");

        ScarecrowSort.main(new String[0]);

        assertOutputFileContent("YES");
    }

    @Test
    public void test() {
        writeToInput("6 2\n2 1 4 5 1 3");

        ScarecrowSort.main(new String[0]);

        assertOutputFileContent("YES");
    }
}