package datastructure.mystack;

public interface Stack<E> {
    E pop();  // 出栈

    void push(E e); // 入栈

    E peek(); // 查看栈顶的元素

    int getSize(); // 查看栈的大小

    boolean isEmpty(); // 栈是否为空

}
