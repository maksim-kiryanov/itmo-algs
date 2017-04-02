package itmo_algs.week_1;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class SwapSecretaryTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("5\n3 1 4 2 2");

        SwapSecretary.main(new String[0]);

        assertOutputFileContent("Swap elements at indices 3 and 5.\n" +
                "Swap elements at indices 1 and 4.\n" +
                "Swap elements at indices 1 and 2.\n" +
                "No more swaps needed.\n" +
                "1 2 2 3 4");
    }

    @Test
    public void testDescendantVariant() {
        writeToInput("3\n5 3 1");

        SwapSecretary.main(new String[0]);

        assertOutputFileContent("Swap elements at indices 1 and 3.\n" +
                "No more swaps needed.\n" +
                "1 3 5");
    }


}