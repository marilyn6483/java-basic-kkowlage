package datastructure.mystack;

import java.util.ArrayList;

public class ArrayStack<E> implements Stack<E> {

    private ArrayList<E> array;

    public ArrayStack(int n) {
        array = new ArrayList<>(n);
    }

    public ArrayStack() {
        array = new ArrayList<>();
    }

    @Override
    public E pop() {
        return array.remove(array.size() - 1);
    }

    @Override
    public void push(E e) {
        array.add(e);
    }

    @Override
    public E peek() {
        return array.get(array.size() - 1);
    }

    @Override
    public int getSize() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public static void main(String[] args) {
//        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        String s = "qwerty";

    }
}
