package source;
import java.util.TreeMap;
import java.util.stream.Stream;

public class ABB<T> {
    private TreeMap<Integer, T> data;

    public ABB() {
        this.data = new TreeMap<>();
    }

    public T find(int key) {
        return this.data.get(key);
    }

    public boolean add(int key, T newElement) {
        if (!this.data.containsKey(key)) {
            this.data.put(key, newElement);
            return true;
        }

        return false;
    }

    public boolean remove(int key) {
        if (this.data.containsKey(key)) {
            this.data.remove(key);
            return true;
        }

        return false;
    }
    
    public int size() {
        return this.data.size();
    }

    public T[] allElements(T[] allData) {
        return this.data.values().toArray(allData);
    }

    public Stream<T> stream() {
        return data.values().stream();
    }
}
