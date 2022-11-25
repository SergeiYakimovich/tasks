package yandex;

import java.util.LinkedList;
import java.util.Queue;

class App {

    public static void main(String[] args) throws Exception {
        int n = 10;
        int q = 6;
        int[] arr = {5, 7, 4, 7, 8, 7};
        Node graf = makeGraf(10);

        for(int i = 1; i <= 6; i++) {
            Node child = findNode(graf, arr[i-1]);
            Node parent = child.prev;
            if(parent != null) {
                int tmpN = child.n;
                child.n = parent.n;
                parent.n = tmpN;
                if(parent.left == child) {
                    Node childR = child.right;
                    Node parentR = parent.right;
                    parent.right = childR;
                    if(childR != null) childR.prev = parent;
                    child.right = parentR;
                    if(parentR != null) parentR.prev = child;
                } else {
                    Node childL = child.left;
                    Node parentL = parent.left;
                    parent.left = childL;
                    if(childL != null) childL.prev = parent;
                    child.left = parentL;
                    if(parentL != null) parentL.prev = child;
                }
            }
        }
        showResult(graf);
        System.out.println(); // =>
    }

    public static Node makeGraf(int n) {
        if(n == 0) return null;
        Node head = new Node(1, null, null, null);
        Queue<Node> queue = new LinkedList<>();
        Node current = head;
        for(int i = 2; i <= n; i++){
            if(current.left != null && current.right != null) {
                current = queue.poll();
            }
            Node newNode = new Node(i, null, null, null);
            if(current.left == null) {
                current.left = newNode;
            } else {
                current.right = newNode;
            }
            newNode.prev = current;
            queue.add(newNode);
        }
        return head;
    }

    public static Node findNode(Node top, int val) {
        if(top == null) return null;
        Queue<Node> queue=new LinkedList<> ();
        while(true) {
            if(top.n == val) return top;
            if (top.left!=null) queue.add(top.left);
            if (top.right!=null) queue.add(top.right);
            if (!queue.isEmpty()) top=queue.poll();
            else return null;
        }
    }

    public static void showResult(Node top) {
        if (top != null) {
            showResult(top.left);
            System.out.print(top.n + " ");
            showResult(top.right);
        }
    }
    public static void showGraf(Node top) {
        if(top == null) return;
        Queue<Node> queue=new LinkedList<> ();
        while(true) {
            showNode(top);
            if (top.left!=null) queue.add(top.left);
            if (top.right!=null) queue.add(top.right);
            if (!queue.isEmpty()) top=queue.poll();
            else return;
        }
    }
    public static void showNode(Node top) {
        System.out.print(top.n + " ");
        if(top.prev != null) System.out.print("prev=" + top.prev.n + " ");
        if(top.left != null) System.out.print("left=" + top.left.n + " ");
        if(top.right != null) System.out.print("right=" + top.right.n + " ");
        System.out.println();
    }

}

class Node {
    public int n;
    public Node prev;
    public Node left;
    public Node right;

    public Node(int n) {
        this.n = n;
        this.prev = null;
        this.left = null;
        this.right = null;
    }
    public Node(int n, Node prev, Node nextLeft, Node nextRight) {
        this.n = n;
        this.prev = prev;
        this.left = nextLeft;
        this.right = nextRight;
    }
}


public class YandexGraf {
}
