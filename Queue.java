package prereqchecker;

import java.util.NoSuchElementException;

public class Queue<T> {
    private CLL<T> myList;

    public Queue() {
        myList = new CLL<T>();
    }

    public void enqueue(T element) {
        myList.addLast(element);
    }

    public T dequeue() {
        if (myList.size() > 0) {
            return myList.deleteFirst();
        } else {
            throw new NoSuchElementException();
        }

    }

    public boolean isEmpty() {
        return myList.size() == 0;
    }
}
