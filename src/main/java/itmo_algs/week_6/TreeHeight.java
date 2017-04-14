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
                tree.insert(new Node(key));
            }

            out.println(tree.getHeight());
        } catch (IOException e) {
            throw new RuntimeException("Error while read file input.txt", e);
        }
    }

    static class Tree {
//        private static final int MAX_TREE_SIZE = 200000;
//        private Node[] nodes = new Node[MAX_TREE_SIZE];

        private Node root;

        void insert(Node node) {
            if (root == null) {
                root = node;
                return;
            }

            Node parent = root;
            while (true) {
                if (parent.key < node.key) {
                    if (parent.right == null) {
                        parent.right = node;
                        break;
                    }
                    parent = parent.right;
                } else {
                    if (parent.left == null) {
                        parent.left = node;
                        break;
                    }
                    parent = parent.left;
                }
            }
        }

        int getHeight() {
            return getHeight(root);
        }

        private int getHeight(Node node) {
            if (node == null) {
                return 0;
            }
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    static class Node {
        private int key;
        private Node left;
        private Node right;

        public Node(int key) {
            this.key = key;
        }
    }
}
