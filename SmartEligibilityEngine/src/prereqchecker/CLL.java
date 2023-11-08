package prereqchecker;

public class CLL<T> {
    private Node<T> rear; // is last node of CLL
    private int count;

    public CLL() {
        this.rear = null;
        count = 0;
    }

    public T getRearData() {
        return this.rear.getData();
    }

    public Node<T> getRearNode() {
        return this.rear;
    }

    public T deleteFirst() {
        Node<T> current = this.rear;
        T thisOne = this.rear.getData();
        current = current.getNext();
        while (current.getNext().getData() != thisOne) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        this.rear = current;
        count--;
        return thisOne;

    }

    public boolean delete(T target) {

        T S = rear.getNext().getData();
        Node<T> current = rear.getNext();
        if (target.equals(current.getData())) {
            rear.setNext(current.getNext());
            count--;
            return true;
        } else {
            rear = rear.getNext();
            current = current.getNext();
        }

        while (current.getData() != S) {
            if (target.equals(current.getData())) {
                rear.setNext(current.getNext());
                count--;
                return true;
            }
            rear = rear.getNext();
            current = current.getNext();
        }
        return false;
    }

    public boolean addLast(T newItem) {

        if (this.rear == null) {
            Node<T> newNode = new Node<T>(newItem, null);
            this.rear = newNode;
            this.rear.setNext(rear);

            count++;
            return true;
        } else {
            Node<T> newNode = new Node<T>(newItem, rear.getNext());
            this.rear.setNext(newNode);

            count++;
            return true;
        }

    }

    public void traverse() {
        Node<T> current = this.rear.getNext();
        T S = this.rear.getNext().getData();
        System.out.println(current.getData());
        current = current.getNext();
        while (current.getData() != S) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public int size() {
        return count;
    }

}
