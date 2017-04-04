package itmo_algs.week_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author maksim-kiryanov
 */
public class PriorityQueue {
    private static final int MAX_HEAP_SIZE = 1_000_000;

    public static void main(String[] args) {
        int[] heapIndex = new int[MAX_HEAP_SIZE];

        SwapNodeInterceptor<ValueHolder> interceptor = (nodes, i1, i2) -> {
            heapIndex[nodes[i1].order] = i1;
            heapIndex[nodes[i2].order] = i2;
        };

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            Heap<ValueHolder> heap = new Heap<>(interceptor);

            int commandCount = Integer.parseInt(reader.readLine());
            for (int i = 0, adds = 0; i < commandCount; i++) {
                String[] commandParts = reader.readLine().split(" ");

                switch (commandParts[0]) {
                    case "A":
                        int key = Integer.parseInt(commandParts[1]);
                        heap.add(new ValueHolder(key, adds++));
                        break;
                    case "D":
                        int order = Integer.parseInt(commandParts[1]) - 1;
                        int newKey = Integer.parseInt(commandParts[2]);
                        heap.decreaseElement(order, new ValueHolder(newKey, order));
                        break;
                    case "X":
                        ValueHolder value = heap.dequeue();
                        writer.println(value != null ? value.key : "*");
                        break;
                    default:
                        throw new RuntimeException("Unknown command");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Heap<T extends Comparable<T>> {
        @SuppressWarnings("unchecked")
        private T[] nodes = (T[]) new Comparable[MAX_HEAP_SIZE];
        private int size = 0;
        private SwapNodeInterceptor<T> interceptor;

        public Heap(SwapNodeInterceptor<T> interceptor) {
            this.interceptor = interceptor;
        }

        void add(T node) {
            size++;
            nodes[size - 1] = node;
            decreaseElement(size - 1, node);

            if (size == 1) {
                interceptor.postSwap(nodes, 0, 0);
            }
        }

        void decreaseElement(int index, T node) {
            if (node.compareTo(nodes[index]) > 0) {
                throw new RuntimeException("New node element more than old");
            }

            nodes[index] = node;
            while (index > 0) {
                int parentIndex = getParentIndex(index);
                T parent = nodes[parentIndex];
                if (parent.compareTo(nodes[index]) <= 0) break;
                swap(parentIndex, index);
            }
        }

        T dequeue() {
            if (size == 0) {
                return null;
            }

            T node = nodes[0];

            swap(0, --size);
            heapify(0);

            return node;
        }

        private void heapify(int index) {
            T node = nodes[index];

            int leftChildIndex = 2 * (index + 1) - 1;
            T leftChild = nodes[leftChildIndex];
            int rightChildIndex = 2 * (index + 1);
            T rightChild = nodes[rightChildIndex];

            int localMinimum = index;
            if (leftChildIndex < size && leftChild.compareTo(node) < 0) {
                localMinimum = leftChildIndex;
            }
            if (rightChildIndex < size && rightChild.compareTo(node) < 0) {
                localMinimum = rightChildIndex;
            }

            if (localMinimum != index) {
                swap(index, localMinimum);
                heapify(localMinimum);
            }
        }

        private void swap(int i1, int i2) {
            T tempNode = nodes[i1];
            nodes[i1] = nodes[i2];
            nodes[i2] = tempNode;
            interceptor.postSwap(nodes, i1, i2);
        }

        private int getParentIndex(int index) {
            return index / 2;
        }
    }

    interface SwapNodeInterceptor<T> {
        void postSwap(T[] nodes, int i1, int i2);
    }

    static class ValueHolder implements Comparable<ValueHolder> {
        private int key;
        private int order;

        public ValueHolder(int key, int order) {
            this.key = key;
            this.order = order;
        }

        @Override
        public int compareTo(ValueHolder o) {
            return Integer.compare(key, o.key);
        }
    }
}
