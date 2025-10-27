package ki_301_krylova_lab2;

/**
 * Клас, що представляє операційну систему телефону.
 * Містить інформацію про назву операційної системи та її версію.
 */
public class OperatingSystem {
    private String name;
    private String version;

    /**
     * Конструктор для створення операційної системи з заданою назвою та версією.
     *
     * @param name Назва операційної системи (наприклад, "Android", "iOS").
     * @param version Версія операційної системи (наприклад, "12", "18").
     */
    public OperatingSystem(String name, String version) {
        this.name = name;
        this.version = version;
    }

    /**
     * Повертає назву операційної системи.
     *
     * @return Назва операційної системи.
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає версію операційної системи.
     *
     * @return Версія операційної системи.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Встановлює нову версію операційної системи.
     *
     * @param version Нова версія операційної системи.
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
