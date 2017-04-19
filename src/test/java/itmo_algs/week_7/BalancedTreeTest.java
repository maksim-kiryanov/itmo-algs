package itmo_algs.week_7;

import itmo_algs.util.FileBasedTest;
import itmo_algs.week_6.TreeHeight;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class BalancedTreeTest extends FileBasedTest {
    @Test
    public void testHeight() {
        writeToInput("6\n" +
                "-2 0 2\n" +
                "8 4 3\n" +
                "9 0 0\n" +
                "3 6 5\n" +
                "6 0 0\n" +
                "0 0 0");

        TreeHeight.main(new String[0]);

        assertOutputFileContent("4");
    }

    @Test
    public void testHeight2() {
        writeToInput("0");

        TreeHeight.main(new String[0]);

        assertOutputFileContent("0");
    }

    @Test
    public void testHeight3() {
        writeToInput("21\n" +
                "10000 2 0\n" +
                "9000 3 0\n" +
                "8000 4 0\n" +
                "6000 5 0\n" +
                "3000 6 0\n" +
                "1000 7 0\n" +
                "900 8 0\n" +
                "800 9 0\n" +
                "600 10 0\n" +
                "300 11 0\n" +
                "10 12 0\n" +
                "9 13 0\n" +
                "8 14 0\n" +
                "6 15 0\n" +
                "3 16 0\n" +
                "1 17 0\n" +
                "0 18 0\n" +
                "-3 19 0\n" +
                "-6 20 0\n" +
                "-8 21 0\n" +
                "-9 0 0");

        TreeHeight.main(new String[0]);

        assertOutputFileContent("21");
    }

    @Test
    public void test() {
        writeToInput("6\n" +
                "-2 0 2\n" +
                "8 4 3\n" +
                "9 0 0\n" +
                "3 6 5\n" +
                "6 0 0\n" +
                "0 0 0");

        BalancedTree.main(new String[0]);

        assertOutputFileContent("3\n" +
                "-1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "0");
    }
}