package itmo_algs.week_4;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

/**
 * @author maksim-kiryanov
 */
public class BracketsTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("5\n" +
                "()()\n" +
                "([])\n" +
                "([)]\n" +
                "((]]\n" +
                ")(");

        Brackets.main(new String[0]);

        assertOutputFileContent("YES\n" +
                "YES\n" +
                "NO\n" +
                "NO\n" +
                "NO");
    }
}