package itmo_algs.week_1;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class SortLandTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("5\n10.00 8.70 0.01 5.00 3.00");

        SortLand.main(new String[0]);

        assertOutputFileContent("3 4 1");
    }
}