package datastructure.linkedlist;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Test1 {
    static class Node<E> {
        private E element;
        private Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node(E element) {
            this(element, null);
        }
    }

    // 递归逆序打印链表
    public static void recursiveReverseShow(Node node) {
        if (node.next != null) {
            recursiveReverseShow(node.next);
        }

        System.out.println(node.element);

    }

    public static void stackReverseShow(Node node) {
        Stack<Node> stack = new Stack();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop().element);
        }
    }

    public static void show(Node node) {
        while (node != null) {
            System.out.println(node.element);
            node = node.next;
        }
    }

    // 递归反转单链表，给定一个链表的头节点，反转这个链表
    public static Node reverseByRecursive(Node node) {
        // 空链表
        Node newNode = new Node("", null);
        Node temp = node;
        while (temp.next != null) {

        }

        return newNode;

    }

    // 传入需要反转的头节点，返回反转后的头节点
    public static Node reverse1(Node node) {

        // 只有一个节点的情况，不反转，返回本身
        if (node.next == null) {
            return node;
        }
        // 传入节点为null的情况，返回null

        // 使用双指针，初始化两个节点
        Node pre = null;
        Node cur = node;

        while (cur != null) {
            Node temp = cur.next; // 这是下次的cur节点
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 删除指定节点 使用O(1)的时间复杂度
     * 一般删除链表的节点需要O(n)的时间复杂度。查找待删除的节点O(n),删除O(1)
     * 单链表的情况，将待删除的next复制到待删除的节点，再删除原待删除节点的next就可以完成
     * 假定待删除的节点在链表中
     */
    public static Node deleteNode(Node head, Node deleteNode) {
        // 把下一个节点的内容复制到当前节点，再删除下一个节点，实现O(1)复杂度的删除元素
//        Node temp = deleteNode.next;
        // 只有一个节点的情况
        if (head.next == null) {
            head = null;
            return head;
        }
        // 要删除的节点不是尾节点
        if (deleteNode.next != null) {
            deleteNode.element = deleteNode.next.element;
            deleteNode.next = deleteNode.next.next;
        } else {
            // 待删除的节点是尾节点
            Node cur = head;
            // 找到尾节点的前驱节点
            while (cur.next != deleteNode) {
                cur = cur.next;
            }
            cur.next = null;

        }
        
        return head;
    }

    @Test
    public void test1() {
        Node<String> node5 = new Node<String>("e");
        Node<String> node4 = new Node<String>("d", node5);
        Node<String> node3 = new Node<String>("c", node4);
        Node<String> node2 = new Node<String>("b", node3);
        Node<String> node1 = new Node<String>("a", node2);
        Node<String> node0 = new Node<>("f");
        int i = 0;
        Node<String> cur = node1;
        while (cur != null ) {
            i++;
            cur = cur.next;
        }
        System.out.println(i);
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while (!stack.empty()) {

        }
        int[] nums = {};
        int len = nums.length;
        HashMap<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> item :map.entrySet()) {
            item.getKey();
        }
    }

    public static void main(String[] args) {

        Node<String> node5 = new Node<String>("e");
        Node<String> node4 = new Node<String>("d", node5);
        Node<String> node3 = new Node<String>("c", node4);
        Node<String> node2 = new Node<String>("b", node3);
        Node<String> node1 = new Node<String>("a", node2);
        Node<String> node0 = new Node<>("f");

        //打印链表
        // 创建一个头节点，指向链表的第一个节点，头节点没有数据
        Node<String> head = new Node<String>("", node1);
        Node<String> cur = head;
        show(node0);
        Node newNode = deleteNode(node0, node5); //
        System.out.println("========================");
        show(newNode);

    }
}
