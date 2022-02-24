package datastructure.linkedlist;

public class MyLinkedList<E> {
    private class Node {
        public E element;
        public Node next;

        @Override
        public String toString() {
            return element.toString();
//            return "Node{" +
//                    "element=" + element +
//                    '}';
        }

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node(E element) {
            this(element, null);
        }
    }

    private int size;
    private Node dummyHead;
    private Node tail;

    // 带虚拟头节点的链表
    public MyLinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 在指定位置插入元素
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add fail, illegal index.");
        }

        Node pre = dummyHead;
        // 找到index位置前一个位置的元素
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        // 插入节点
        pre.next = new Node(element, pre.next);
        size++;
    }

    // 头插法
    public void addFist(E element) {
        add(0, element);
    }

    public void addLast(E element) {
        add(size, element);
    }

    // 链表不常用，仅作联系用 :)
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Get fail, illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    public E getFirst() {
        return get(0);
    }

    // 获取链表的大小
    public int getSize() {
        return size;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {

    }
}
