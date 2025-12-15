package ki_301_krylova_lab6;

import java.util.ArrayList;

/**
 * Generic Dictionary class that stores elements of type T.
 * @param <T> the type of elements implementing Entry interface
 */
public class Dictionary<T extends Entry> {
    private final ArrayList<T> entries;

    /** Default constructor */
    public Dictionary() {
        entries = new ArrayList<>();
    }

    /** Adds an element to the dictionary */
    public void addElement(T data) {
        entries.add(data);
        System.out.print("Element added: ");
        data.print();
    }

    /** Removes an element by index */
    public void removeElement(int i) {
        if (i >= 0 && i < entries.size()) {
            System.out.println("Element removed:");
            entries.get(i).print();
            entries.remove(i);
        } else {
            System.out.println("Invalid index for removal.");
        }
    }

    /** Finds the element with the maximum frequency */
    public T findMax() {
        if (entries.isEmpty()) return null;

        T max = entries.get(0);
        for (int i = 1; i < entries.size(); i++) {
            if (entries.get(i).compareTo(max) > 0) {
                max = entries.get(i);
            }
        }
        return max;
    }

    /** Prints all elements in the dictionary */
    public void printAll() {
        for (T e : entries) {
            e.print();
        }
    }
}
