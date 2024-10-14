package yandex.hrtech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BinaryTreeHeight { // #10
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] parts = reader.readLine().split(" ");
        int[] nums = new int[parts.length - 1];
        for (int i = 0; i < parts.length - 1; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        Node top = new Node(nums[0]);
        for(int i = 1; i < nums.length; i++) {
            addNode(top, nums[i]);
        }
        int result = countHeight(top);

        writer.write(String.valueOf(result));

        reader.close();
        writer.close();
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    static Node addNode(Node node, int n) {
        if(node == null) return new Node(n);

        if(node.val < n) {
            node.left = addNode(node.left, n);
        } else if(node.val > n) {
            node.right = addNode(node.right, n);
        }
        return node;
    }

    static int countHeight(Node node) {
        if(node == null) return 0;
        int leftHeight = countHeight(node.left) + 1;
        int rightHeight = countHeight(node.right) + 1;
        return Math.max(leftHeight, rightHeight);
    }

}
