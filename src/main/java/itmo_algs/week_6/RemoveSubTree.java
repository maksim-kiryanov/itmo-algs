package itmo_algs.week_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * @author maksim-kiryanov
 */
public class RemoveSubTree {
    public static void main(String[] args) {
        try (BufferedReader in = Files.newBufferedReader(Paths.get("input.txt"));
             PrintWriter out = new PrintWriter(Files.newBufferedWriter(Paths.get("output.txt")))) {
            int n = Integer.parseInt(in.readLine());

            StringTokenizer tokenizer = null;
            Tree tree = new Tree();
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(in.readLine());
                int key = Integer.parseInt(tokenizer.nextToken());
                int left = Integer.parseInt(tokenizer.nextToken()) - 1;
                int right = Integer.parseInt(tokenizer.nextToken()) - 1;
                tree.insert(i, new Node(key, left, right));
            }

            int m = Integer.parseInt(in.readLine());

            tokenizer = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                int key = Integer.parseInt(tokenizer.nextToken());
                tree.deleteSubTree(key);
                out.println(tree.getSize());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    static class Tree {
        private static final int MAX_TREE_SIZE = 200000;
        private static final int EMPTY_NODE = -1;

        private Node[] nodes = new Node[MAX_TREE_SIZE];
        private int size;

        void insert(int index, Node node) {
            if (checkRange(index)) {
                nodes[index] = node;
                size++;
            }
        }

        private Node getNode(int index) {
            return checkRange(index) ? nodes[index] : null;
        }

        private boolean checkRange(int index) {
            return index >= 0 && index < nodes.length;
        }

        int getHeight() {
            return getHeight(nodes[0]);
        }

        private int getHeight(Node node) {
            if (node == null) {
                return 0;
            }
            return 1 + Math.max(getHeight(getNode(node.left)), getHeight(getNode(node.right)));
        }

        void deleteSubTree(int key) {
            Node current = nodes[0];
            Node parent = current;
            boolean leftNode = true;
            while (current != null && current.key != key) {
                parent = current;
                if (current.key < key) {
                    current = getNode(current.right);
                    leftNode = false;
                } else {
                    current = getNode(current.left);
                    leftNode = true;
                }
            }

            if (current != null) {
                size -= size(current);
                if (leftNode) {
                    parent.left = EMPTY_NODE;
                } else {
                    parent.right = EMPTY_NODE;
                }
            }
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }
            return 1 + size(getNode(node.left)) + size(getNode(node.right));
        }

        public int getSize() {
            return size;
        }
    }

    static class Node {
        private int key;
        private int left;
        private int right;

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, int left, int right) {
            this(key);
            this.left = left;
            this.right = right;
        }
    }
}
