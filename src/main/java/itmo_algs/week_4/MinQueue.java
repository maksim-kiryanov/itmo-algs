package itmo_algs.week_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author maksim-kiryanov
 */
public class MinQueue {
    private static final String ENQUEUE_COMMAND = "+";
    private static final String DEQUEUE_COMMAND = "-";
    private static final String GET_MIN_COMMAND = "?";

    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {

            int commandCount = Integer.parseInt(reader.readLine());

            ArrayBasedMinQueue queue = new ArrayBasedMinQueue();
            Dequeue minDequeue = new Dequeue();
            for (int i = 0; i < commandCount; i++) {
                String[] parts = reader.readLine().split(" ");

                String command = parts[0];
                if (ENQUEUE_COMMAND.equals(command)) {
                    int element = Integer.parseInt(parts[1]);
                    queue.enqueue(element);

                    if (minDequeue.isEmpty()) {
                        minDequeue.enqueueFirst(element);
                        continue;
                    }

                    while (!minDequeue.isEmpty() && minDequeue.pollLast() > element) {
                        minDequeue.dequeueLast();
                    }
                    minDequeue.enqueueLast(element);
                } else if (DEQUEUE_COMMAND.equals(command)) {
                    int element = queue.dequeue();
                    if (element == minDequeue.pollFirst()) {
                        minDequeue.dequeueFirst();
                    }
                } else if (GET_MIN_COMMAND.equals(command)) {
                    writer.println(minDequeue.pollFirst());
                } else {
                    throw new RuntimeException("Unknown command [" + command + "]");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    public static class ArrayBasedMinQueue {
        private static final int MAX_QUEUE_SIZE = 1_000_000;

        private int[] array = new int[MAX_QUEUE_SIZE];
        private int head = -1;
        private int tail = 0;

        void enqueue(int e) {
            if (tail == head - 1) {
                throw new IllegalStateException("Queue is full");
            }

            array[tail] = e;

            if (head == -1) {
                head = 0;
            }

            tail++;
            if (tail == array.length) {
                tail = 0;
            }
        }

        int dequeue() {
            checkIsEmpty();

            int e = array[head++];

            if (head == array.length) {
                head = 0;
            }
            return e;
        }

        boolean isEmpty() {
            return head == -1 || head == tail;
        }

        void checkIsEmpty() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
        }
    }

    static class Dequeue {
        private static final int MAX_QUEUE_SIZE = 1_000_000;

        private int[] array = new int[MAX_QUEUE_SIZE];

        private int head = -1;
        private int tail = 0;

        void enqueueFirst(int e) {
            int newHead = head - 1 >= 0 ? head - 1 : array.length - 1;
            if (newHead == tail) {
                throw new IllegalStateException("Queue is full");
            }

            array[newHead] = e;
            head = newHead;
        }

        void enqueueLast(int e) {
            if (tail == head - 1) {
                throw new IllegalStateException("Queue is full");
            }

            array[tail++] = e;

            if (head == -1) {
                head = 0;
            }

            if (tail == array.length) {
                tail = 0;
            }
        }

        int dequeueFirst() {
            checkIsEmpty();
            int e = array[head];
            head = head + 1 < array.length ? head + 1 : 0;
            return e;
        }

        int dequeueLast() {
            checkIsEmpty();
            tail = tail - 1 >= 0 ? tail - 1 : array.length - 1;
            return array[tail];
        }

        int pollFirst() {
            checkIsEmpty();
            return array[head];
        }

        int pollLast() {
            checkIsEmpty();
            return array[tail - 1 >= 0 ? tail - 1 : array.length - 1];
        }

        boolean isEmpty() {
            return head == -1 || head == tail;
        }

        void checkIsEmpty() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
        }
    }
}
