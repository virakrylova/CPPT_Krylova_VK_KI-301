package ki_301_krylova_lab2;

/**
 * Клас, що представляє екран телефону.
 * Містить інформацію про розмір екрану, його тип та яскравість.
 */
public class Screen {
    private double size;
    private String type;
    private int brightness;

    /**
     * Конструктор для створення екрану з заданим розміром та типом.
     * За замовчуванням яскравість встановлюється на 50%.
     *
     * @param size Розмір екрану у дюймах.
     * @param type Тип екрану (наприклад, "LCD", "OLED").
     */
    public Screen(double size, String type) {
        this.size = size;
        this.type = type;
        this.brightness = 50; // Значення за замовчуванням
    }

    /**
     * Встановлює яскравість екрану.
     * Значення яскравості обмежується в діапазоні від 0 до 100.
     *
     * @param brightness Нове значення яскравості (від 0 до 100).
     */
    public void setBrightness(int brightness) {
        this.brightness = Math.max(0, Math.min(100, brightness));
    }

    /**
     * Повертає розмір екрану.
     *
     * @return Розмір екрану у дюймах.
     */
    public double getSize() {
        return size;
    }

    /**
     * Повертає тип екрану.
     *
     * @return Тип екрану (наприклад, "LCD", "OLED").
     */
    public String getType() {
        return type;
    }
}
