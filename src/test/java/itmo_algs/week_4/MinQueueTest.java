package itmo_algs.week_4;

import itmo_algs.util.FileBasedTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author maksim-kiryanov
 */
public class MinQueueTest extends FileBasedTest {
    @Test
    public void testSimpleVariant() {
        writeToInput("7\n" +
                "+ 1\n" +
                "?\n" +
                "+ 10\n" +
                "?\n" +
                "-\n" +
                "?\n" +
                "-");

        MinQueue.main(new String[0]);

        assertOutputFileContent("1\n" +
                "1\n" +
                "10");
    }

    @Test
    public void testFailed4() {
        writeToInput("5\n" +
                "+ 2\n" +
                "+ 1\n" +
                "?\n" +
                "-\n" +
                "?\n");

        MinQueue.main(new String[0]);

        assertOutputFileContent("1\n" +
                "1");
    }

    @Test
    public void testDequeue() {
        MinQueue.Dequeue dequeue = new MinQueue.Dequeue();

        dequeue.enqueueFirst(1);
        dequeue.enqueueFirst(2);
        dequeue.enqueueFirst(3);
        dequeue.enqueueLast(4);

        assertThat(dequeue.dequeueFirst(), is(3));
        assertThat(dequeue.dequeueFirst(), is(2));
        assertThat(dequeue.dequeueFirst(), is(1));
        assertThat(dequeue.dequeueFirst(), is(4));
    }
}