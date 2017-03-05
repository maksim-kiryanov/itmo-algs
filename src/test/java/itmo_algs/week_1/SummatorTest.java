package itmo_algs.week_1;

import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class SummatorTest extends FileBasedTest {
    @Test
    public void testSimpleSum_1Plus2() {
        writeToInput("1 2");

        Summator.main(new String[0]);

        assertOutputFileContent("3");
    }

    @Test
    public void testSimpleSum_PositivePlusNegative() {
        writeToInput("1 -2");

        Summator.main(new String[0]);

        assertOutputFileContent("-1");
    }

    @Test
    public void testSum_MaxPositiveValues() {
        writeToInput("1000000000 1000000000");

        Summator.main(new String[0]);

        assertOutputFileContent("2000000000");
    }

    @Test
    public void testSum_MaxNegativeValues() {
        writeToInput("-1000000000 -1000000000");

        Summator.main(new String[0]);

        assertOutputFileContent("-2000000000");
    }
}
