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
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("input.txt"));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get("output.txt"));
             PrintWriter writer = new PrintWriter(bw)) {
            OrderNodeDetector detector = new OrderNodeDetector();
            Heap heap = new Heap(detector);

            int commandCount = Integer.parseInt(reader.readLine());
            for (int i = 0, adds = 0; i < commandCount; i++) {
                String[] commandParts = reader.readLine().split(" ");

                switch (commandParts[0]) {
                    case "A":
                        int key = Integer.parseInt(commandParts[1]);
                        heap.add(new Node(key, adds++));
                        break;
                    case "D":
                        int order = Integer.parseInt(commandParts[1]) - 1;
                        int currentIndex = detector.getIndex(order);
                        int newKey = Integer.parseInt(commandParts[2]);
                        heap.decreaseElement(currentIndex, new Node(newKey, order));
                        break;
                    case "X":
                        Node value = heap.dequeue();
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

    static class Heap {
        private Node[] nodes = new Node[MAX_HEAP_SIZE];
        private int size = 0;
        private OrderNodeDetector detector;

        public Heap(OrderNodeDetector detector) {
            this.detector = detector;
        }

        void add(Node node) {
            size++;
            nodes[size - 1] = node;
            detector.initOrder(node, size - 1);

            decreaseElement(size - 1, node);
        }

        void decreaseElement(int index, Node node) {
            if (node.compareTo(nodes[index]) > 0) {
                throw new RuntimeException("New node element more than old");
            }

            nodes[index] = node;
            while (index > 0) {
                int parentIndex = getParentIndex(index);
                Node parent = nodes[parentIndex];
                if (parent.compareTo(nodes[index]) <= 0) break;
                swap(parentIndex, index);
            }
        }

        Node dequeue() {
            if (size == 0) {
                return null;
            }

            Node node = nodes[0];

            swap(0, size - 1);
            size--;
            heapify(0);

            return node;
        }

        private void heapify(int index) {
            Node node = nodes[index];

            int leftChildIndex = 2 * (index + 1) - 1;
            Node leftChild = nodes[leftChildIndex];
            int rightChildIndex = 2 * (index + 1);
            Node rightChild = nodes[rightChildIndex];

            int localMinimumIndex = index;
            if (leftChildIndex < size && leftChild.compareTo(node) < 0) {
                localMinimumIndex = leftChildIndex;
            }
            if (rightChildIndex < size && rightChild.compareTo(node) < 0) {
                localMinimumIndex = rightChildIndex;
            }

            if (localMinimumIndex != index) {
                swap(index, localMinimumIndex);
                heapify(localMinimumIndex);
            }
        }

        private void swap(int i1, int i2) {
            Node tempNode = nodes[i1];
            nodes[i1] = nodes[i2];
            nodes[i2] = tempNode;
            detector.detectSwap(nodes, i1, i2);
        }

        private int getParentIndex(int index) {
            return (index - 1) / 2;
        }
    }

    static class OrderNodeDetector {
        int[] heapIndex = new int[MAX_HEAP_SIZE];

        void initOrder(Node node, int index) {
            heapIndex[node.order] = index;
        }

        void detectSwap(Node[] nodes, int i1, int i2) {
            heapIndex[nodes[i1].order] = i1;
            heapIndex[nodes[i2].order] = i2;
        }

        int getIndex(int order) {
            return heapIndex[order];
        }
    }

    static class Node implements Comparable<Node> {
        private int key;
        private int order;

        public Node(int key, int order) {
            this.key = key;
            this.order = order;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(key, o.key);
        }
    }
}
