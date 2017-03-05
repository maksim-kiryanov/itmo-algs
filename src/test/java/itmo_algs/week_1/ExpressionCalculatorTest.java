package itmo_algs.week_1;

import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class ExpressionCalculatorTest extends FileBasedTest {
    @Test
    public void testSimpleSum() {
        writeToInput("23 11");

        ExpressionCalculator.main(new String[0]);

        assertOutputFileContent("144");
    }

    @Test
    public void testSum_PositiveAndNegative() {
        writeToInput("1 -2");

        ExpressionCalculator.main(new String[0]);

        assertOutputFileContent("5");
    }

    @Test
    public void testSum_NegativeAndPositive() {
        writeToInput("-100 1");

        ExpressionCalculator.main(new String[0]);

        assertOutputFileContent("-99");
    }

    @Test
    public void testSum_MaxPositiveValues() {
        writeToInput("1000000000 1000000000");

        ExpressionCalculator.main(new String[0]);

        assertOutputFileContent("1000000001000000000");
    }

    @Test
    public void testSum_MaxNegativeValues() {
        writeToInput("-1000000000 -1000000000");

        ExpressionCalculator.main(new String[0]);

        assertOutputFileContent("999999999000000000");
    }
}