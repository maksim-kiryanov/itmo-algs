package itmo_algs.week_2;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class InversionCounterTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("10\n1 8 2 1 4 7 3 2 3 6");

        InversionCounter.main(new String[0]);

        assertOutputFileContent("17");
    }

    @Test
    public void testDescendantVariant() {
        writeToInput("10\n8 7 6 4 3 3 2 2 1 1");

        InversionCounter.main(new String[0]);

        assertOutputFileContent("42");
    }
}