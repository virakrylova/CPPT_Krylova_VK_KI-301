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
        if (max != null) {
            max.print();
        } else {
            System.out.println("Dictionary is empty.");
        }

        System.out.println("\nAll dictionary elements:");
        dict.printAll();

        dict.removeElement(1);
        System.out.println("\nAfter removing element at index 1:");
        dict.printAll();
    }
}
