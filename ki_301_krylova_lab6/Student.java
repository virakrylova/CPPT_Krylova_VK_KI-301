package ki_301_krylova_lab6;

/**
 * Допоміжний клас, що представляє студента.
 * Використовується як один з типів (значення) для демонстрації
 * роботи параметризованого класу {@link Dictionary}.
 */
public class Student {
    private String name;
    private int studentId;

    /**
     * Конструктор для створення об'єкта Студент.
     *
     * @param name      Ім'я студента.
     * @param studentId Номер студентського квитка.
     */
    public Student(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public int getStudentId() {
        return studentId;
    }

    /**
     * Повертає рядкове представлення об'єкта.
     *
     * @return Рядок з іменем та ID студента.
     */
    @Override
    public String toString() {
        return "Student(Name: " + name + ", ID: " + studentId + ")";
    }
}