package ki_301_krylova_lab6;

/**
 * Class representing a phrase in the dictionary.
 */
public class Phrase implements Entry {
    private final String englishPhrase;
    private final String frenchPhrase;
    private final int frequency;

    /**
     * Constructor for creating a new phrase.
     * @param eng the English phrase
     * @param fr the French translation
     * @param freq frequency of usage
     */
    public Phrase(String eng, String fr, int freq) {
        englishPhrase = eng;
        frenchPhrase = fr;
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
        System.out.println("Phrase: " + englishPhrase + " - " + frenchPhrase + " (Frequency: " + frequency + ")");
    }
}
