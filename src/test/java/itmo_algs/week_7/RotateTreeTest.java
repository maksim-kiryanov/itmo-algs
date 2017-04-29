package itmo_algs.week_7;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class RotateTreeTest extends FileBasedTest {
    @Test
    public void testLeftRotate() {
        writeToInput("7\n" +
                "-2 7 2\n" +
                "8 4 3\n" +
                "9 0 0\n" +
                "3 6 5\n" +
                "6 0 0\n" +
                "0 0 0\n" +
                "-7 0 0");

        RotateTree.main(new String[0]);

        assertOutputFileContent("7\n" +
                "3 2 3\n" +
                "-2 4 5\n" +
                "8 6 7\n" +
                "-7 0 0\n" +
                "0 0 0\n" +
                "6 0 0\n" +
                "9 0 0");
    }
}