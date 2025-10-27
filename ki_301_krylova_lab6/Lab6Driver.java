package ki_301_krylova_lab6;

/**
 * Клас-драйвер для тестування параметризованого класу {@link Dictionary}.
 * * Демонструє використання словника з двома різними наборами типів:
 * 1. Словник частоти слів (String, Integer).
 * 2. Реєстр студентів (Integer, Student).
 */
public class Lab6Driver {

    /**
     * Головний метод, що запускає демонстрацію.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {

        System.out.println("================================================");
        System.out.println("Demonstration 1: Dictionary <String, Integer>");
        System.out.println("================================================");

        // Створюємо екземпляр класу-контейнера з типами <String, Integer>
        Dictionary<String, Integer> wordFrequency = new Dictionary<>();

        // 1. Розміщення елементів
        wordFrequency.add("Java", 15);
        wordFrequency.add("Generic", 8);
        wordFrequency.add("Programming", 12);
        wordFrequency.add("Apple", 5); // Для перевірки min

        wordFrequency.displayContents();
        System.out.println();

        // 2. Виймання (отримання)
        System.out.println("Frequency of word 'Java': " + wordFrequency.get("Java"));
        System.out.println("Frequency of word 'Python': " + wordFrequency.get("Python")); // неіснуючий
        System.out.println();

        // 3. Пошук Min/Max (оберіть потрібний метод)
        try {
            // **Для ПАРНИХ варіантів (мінімум):**
            System.out.println("Minimum key (first word alphabetically): " + wordFrequency.findMinKey());

            // **Для НЕПАРНИХ варіантів (максимум):**
            System.out.println("Maximum key (last word alphabetically): " + wordFrequency.findMaxKey());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // 4. Виймання (видалення)
        wordFrequency.remove("Generic");
        wordFrequency.displayContents();
        System.out.println();


        System.out.println("================================================");
        System.out.println("Demonstration 2: Dictionary <Integer, Student>");
        System.out.println("================================================");

        // Створюємо екземпляр з іншими типами <Integer, Student>
        // Це задовольняє вимогу про "2 різні класи" (тут Student та Integer)
        Dictionary<Integer, Student> studentRegistry = new Dictionary<>();

        // Створюємо екземпляри 2-го класу
        Student s1 = new Student("Olena Krylova", 301);
        Student s2 = new Student("Ivan Petrenko", 105);
        Student s3 = new Student("Maria Sydorenko", 512);

        // 1. Розміщення елементів (ключ - ID студента)
        studentRegistry.add(s1.getStudentId(), s1);
        studentRegistry.add(s2.getStudentId(), s2);
        studentRegistry.add(s3.getStudentId(), s3);

        studentRegistry.displayContents();
        System.out.println();

        // 2. Виймання (отримання)
        System.out.println("Student with ID 105: " + studentRegistry.get(105));
        System.out.println();

        // 3. Пошук Min/Max (оберіть потрібний метод)
        try {
            // **Для ПАРНИХ варіантів (мінімум):**
            System.out.println("Minimum key (smallest ID): " + studentRegistry.findMinKey());

            // **Для НЕПАРНИХ варіантів (максимум):**
            System.out.println("Maximum key (largest ID): " + studentRegistry.findMaxKey());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
        
        // 4. Виймання (видалення)
        studentRegistry.remove(301); // Видаляємо Олену
        studentRegistry.displayContents();
    }
}