package datastructure.mytree;

public class BST<E extends Comparable<E>> {
    private class Node<E> {
        private E e;
        private Node left;
        private Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node<E> root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
//        if (root == null) {
//            root = new Node<E>(e);
//            size++;
//        } else {
//            add(root, e);
//        }
        root = add(root, e);

    }

    private Node add(Node node, E e) {

        if (node == null) {
            node =  new Node(e);
        } else if (e.compareTo((E)node.e) == 0) {
            return node;
        } else if (e.compareTo((E) node.e) <0) {
            node.left = add(node.left, e);
        } else {
            node.right = add(node.right, e);
        }
        return node;


//        if (e.equals(node.e)) {
//            return;
//        } else if (e.compareTo((E) node.e) > 0 && node.right == null) {
//            node.right = new Node(e);
//            return;
//        } else if (e.compareTo((E) node.e) < 0 && node.left == null) {
//            node.left = new Node(e);
//            return;
//        }
//        if (e.compareTo((E) node.e) < 0) {
//            add(node.left, e);
//        } else {
//            add(node.right, e);
//        }
    }

    public void printBst() {
        
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(2);
        bst.add(10);
        bst.add(5);
        bst.add(11);

    }

}
