package prereqchecker;

public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) { // constructor
        this.data = data;
        this.next = next;
    }

    public boolean equalsz(T n) {
        if (this.data == null) {
            return false;
        } else
            return (this.data == n);
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setData(T enteredData) {
        this.data = enteredData;
    }

    public void setNext(Node<T> enteredNext) {
        this.next = enteredNext;
    }
}
