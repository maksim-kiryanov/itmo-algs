package itmo_algs.week_5;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class HeapTest extends FileBasedTest {
    @Test
    public void testNegativeVariant() {
        writeToInput("5\n" +
                "1 0 1 2 0");

        Heap.main(new String[0]);

        assertOutputFileContent("NO");
    }

    @Test
    public void testPositiveVariant() {
        writeToInput("5\n" +
                "1 3 2 5 4");

        Heap.main(new String[0]);

        assertOutputFileContent("YES");
    }
}