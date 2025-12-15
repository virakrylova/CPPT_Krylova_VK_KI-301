package ki_301_krylova_lab6;

/**
 * Entry interface – base type for dictionary elements.
 */
public interface Entry extends Comparable<Entry> {
    int getFrequency();
    void print();
}
