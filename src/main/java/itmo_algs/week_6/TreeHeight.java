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
public class TreeHeight {
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

            out.println(tree.getHeight());
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    static class Tree {
        private static final int MAX_TREE_SIZE = 200000;
        private Node[] nodes = new Node[MAX_TREE_SIZE];

        void insert(int index, Node node) {
            if (checkRange(index)) {
                nodes[index] = node;
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
