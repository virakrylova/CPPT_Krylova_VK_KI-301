package ki_301_krylova_lab6;

/**
 * Class representing a single word in the dictionary.
 */
public class Word implements Entry {
    private final String english;
    private final String french;
    private final int frequency;

    /**
     * Constructor for creating a new word.
     * @param eng the English word
     * @param fr the French translation
     * @param freq frequency of usage
     */
    public Word(String eng, String fr, int freq) {
        english = eng;
        french = fr;
        frequency = freq;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Entry e) {
        return Integer.compare(frequency, e.getFrequency());
    }

    @Override
    public void print() {
        System.out.println("Word: " + english + " - " + french + " (Frequency: " + frequency + ")");
    }
}
