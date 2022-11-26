package source;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Lista<T> {
    private LinkedList<T> data;
    public Lista() {
        this.data = new LinkedList<>();
    }

    public boolean add(T newElement) {
        return this.data.add(newElement);
    }

    public T find(int index) {
        return this.data.get(index);
    }

    public boolean remove(T element) {
        return this.data.remove(element);
    }

    public boolean contains(T element) {
        return this.data.contains(element);
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public T[] allElements(T[] allData) {
        return this.data.toArray(allData);
    }

    public int size() {
        return this.data.size();
    }

    public T pop() {
        return this.data.pop();
    }

    public void push(T element) {
        this.data.push(element);
    }

    public Stream<T> stream() {
        return this.data.stream();
    }

    public T getFirt() {
        return this.data.getFirst();
    }

    public T getLast() {
        return this.data.getLast();
    }
}
