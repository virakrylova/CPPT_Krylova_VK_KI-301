/****************************************************************************
 * Copyright (c) 2013-2025 Lviv Polytechnic National University.
 * All Rights Reserved.
 *
 * This program and the accompanying materials are made available under the terms
 * of the Academic Free License v. 3.0 which accompanies this distribution, and is
 * available at https://opensource.org/license/afl-3-0-php/
 *
 * SPDX-License-Identifier: AFL-3.0
 ****************************************************************************/

package ki_301_krylova_lab6;
import java.util.*;

/**
 * Driver class for demonstrating the parameterized Dictionary class.
 * Goal: to master parameterized programming in Java.
 */
public class DictionaryDriver {
    public static void main(String[] args) {
        Dictionary<Entry> dict = new Dictionary<>();

        dict.addElement(new Word("apple", "pomme", 95));
        dict.addElement(new Word("banana", "banane", 80));
        dict.addElement(new Phrase("break a leg", "bonne chance", 150));
        dict.addElement(new Phrase("once in a blue moon", "très rarement", 120));

        Entry max = dict.findMax();
        System.out.println("\nThe most frequent element in the dictionary is:");
        max.print();

        System.out.println("\nAll dictionary elements:");
        dict.printAll();

        dict.removeElement(1);
        System.out.println("\nAfter removing element at index 1:");
        dict.printAll();
    }
}

/**
 * Generic Dictionary class that stores elements of type T.
 * @param <T> the type of elements implementing Entry interface
 */
class Dictionary<T extends Entry> {
    private ArrayList<T> entries;

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

/**
 * Entry interface – base type for dictionary elements.
 */
interface Entry extends Comparable<Entry> {
    int getFrequency();
    void print();
}

/**
 * Class representing a single word in the dictionary.
 */
class Word implements Entry {
    private String english;
    private String french;
    private int frequency;

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

/**
 * Class representing a phrase in the dictionary.
 */
class Phrase implements Entry {
    private String englishPhrase;
    private String frenchPhrase;
    private int frequency;

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
