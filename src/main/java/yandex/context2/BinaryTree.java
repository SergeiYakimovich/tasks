package yandex.context2;

import java.util.*;

public class BinaryTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int v = scanner.nextInt();

        Map<Integer, Node> nodesMap = new HashMap<>();
        List<InputElement> inputElements = new ArrayList<>();

        Node root = new Node(v);
        nodesMap.put(1, root);

        for (int i = 2; i <= n; i++) {
            int k = scanner.nextInt();
            int p = scanner.nextInt();
            char c = scanner.next().charAt(0);
            inputElements.add(new InputElement(i, k, p, c));
            nodesMap.put(i, new Node(k));
        }

        for (InputElement element : inputElements) {
            Node node = nodesMap.get(element.i);
            if (element.c == 'L') {
                nodesMap.get(element.p).left = node;
            } else {
                nodesMap.get(element.p).right = node;
            }
        }

        countNodes(root); // Посчитаем количество узлов в каждом поддереве
        int l = scanner.nextInt();
        int r = scanner.nextInt();

        System.out.println(countReplacements(root, l, r, 1, 1000000000));
    }

    static int countNodes(Node node) {
        if (node == null) {
            return 0;
        }

        // Рекурсивно обойдем дерево, сначала посчитаем количество узлов в левом поддереве,
        // потом в правом поддереве, и сложим их с учетом корневого узла
        node.nodesCount = 1 + countNodes(node.left) + countNodes(node.right);
        return node.nodesCount;
    }

    static int countReplacements(Node root, int l, int r, int lowLimit, int upLimit) {
        if (root == null) {
            return 0;
        }

        int replacements = 0;
        if (root.key < l || root.key > r) {
            return countReplacements(root.left, l, r, lowLimit, upLimit) +
                    countReplacements(root.right, l, r, lowLimit, upLimit);
        }

        if (root.key >= lowLimit && root.key <= upLimit) {
            replacements++;
        }

        int leftCount = (root.left != null) ? root.left.nodesCount : 0;
        int rightCount = (root.right != null) ? root.right.nodesCount : 0;
        replacements += countReplacements(root.left, l, r, lowLimit, root.key - 1) +
                countReplacements(root.right, l, r, root.key + 1, upLimit) +
                (leftCount + rightCount); // Добавляем количество узлов в левом и правом поддеревьях

        return replacements;
    }

    static class Node {
        int key;
        Node left;
        Node right;
        int nodesCount; // поле для хранения количества узлов в поддереве

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.nodesCount = 1;
        }
    }

    static class InputElement {
        int i;
        int k;
        int p;
        char c;

        public InputElement(int i, int k, int p, char c) {
            this.i = i;
            this.k = k;
            this.p = p;
            this.c = c;
        }
    }
}