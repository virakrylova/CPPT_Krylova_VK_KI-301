package ki_301_krylova_lab6;

import java.util.TreeMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Параметризований клас, що реалізує "Словник" (асоціативний масив).
 * Зберігає пари ключ-значення та автоматично сортує їх за ключем.
 * * @param <K> Тип ключа. Має реалізовувати інтерфейс {@link Comparable} для сортування
 * та пошуку min/max.
 * @param <V> Тип значення, що зберігається у словнику.
 */
public class Dictionary<K extends Comparable<K>, V> {

    // Використовуємо TreeMap, оскільки він зберігає ключі відсортованими,
    // що дозволяє легко знаходити min/max.
    private Map<K, V> storage;

    /**
     * Конструктор за замовчуванням. Ініціалізує порожній словник.
     */
    public Dictionary() {
        this.storage = new TreeMap<>();
    }

    /**
     * Метод 1: Розміщення (додавання або оновлення) елемента у словнику.
     *
     * @param key   Ключ елемента.
     * @param value Значення елемента.
     */
    public void add(K key, V value) {
        if (key == null) {
            System.err.println("Error: Key cannot be null.");
            return;
        }
        storage.put(key, value);
        System.out.println("Added/Updated: [" + key + " = " + value + "]");
    }

    /**
     * Метод 2: Виймання (отримання) елемента за ключем.
     *
     * @param key Ключ, за яким шукається значення.
     * @return Значення, пов'язане з ключем, або null, якщо ключ не знайдено.
     */
    public V get(K key) {
        return storage.get(key);
    }

    /**
     * Метод 3: Виймання (видалення) елемента за ключем.
     *
     * @param key Ключ елемента, який потрібно видалити.
     * @return Значення, яке було видалено, або null, якщо ключ не знайдено.
     */
    public V remove(K key) {
        V removedValue = storage.remove(key);
        if (removedValue != null) {
            System.out.println("Removed: [" + key + "]");
        } else {
            System.out.println("Element with key '" + key + "' not found.");
        }
        return removedValue;
    }

    /**
     * Метод 4 (для ПАРНИХ варіантів): Пошук мінімального елементу (ключа).
     *
     * @return Найменший ключ у словнику.
     * @throws NoSuchElementException якщо словник порожній.
     */
    public K findMinKey() {
        if (storage.isEmpty()) {
            throw new NoSuchElementException("Dictionary is empty, cannot find minimum.");
        }
        // Завдяки TreeMap, firstKey() - це і є мінімальний ключ.
        return ((TreeMap<K, V>) storage).firstKey();
    }

    /**
     * Метод 5 (для НЕПАРНИХ варіантів): Пошук максимального елементу (ключа).
     *
     * @return Найбільший ключ у словнику.
     * @throws NoSuchElementException якщо словник порожній.
     */
    public K findMaxKey() {
        if (storage.isEmpty()) {
            throw new NoSuchElementException("Dictionary is empty, cannot find maximum.");
        }
        // Завдяки TreeMap, lastKey() - це і є максимальний ключ.
        return ((TreeMap<K, V>) storage).lastKey();
    }

    /**
     * Допоміжний метод для виведення вмісту словника.
     */
    public void displayContents() {
        if (storage.isEmpty()) {
            System.out.println("Dictionary is empty.");
            return;
        }
        System.out.println("Dictionary contents:");
        for (Map.Entry<K, V> entry : storage.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}