package ki_301_krylova_lab2;

/**
 * Клас, що представляє батарею телефону.
 * Містить інформацію про рівень заряду та ємність батареї,
 * а також методи для заряджання батареї.
 */
public class Battery {
    private int chargeLevel;
    private int capacity;

    /**
     * Конструктор для створення батареї з заданим рівнем заряду та ємністю.
     * @param chargeLevel Початковий рівень заряду батареї (у відсотках).
     * @param capacity Ємність батареї (у mAh).
     */
    public Battery(int chargeLevel, int capacity) {
        this.chargeLevel = chargeLevel;
        this.capacity = capacity;
    }

    /**
     * Заряджає батарею протягом певної кількості хвилин.
     * Рівень заряду збільшується, але не перевищує 100%.
     * @param minutes Кількість хвилин зарядки.
     */
    public void charge(int minutes) {
        chargeLevel = Math.min(100, chargeLevel + minutes / 3);
    }

    /**
     * Повертає поточний рівень заряду батареї.
     * @return Рівень заряду батареї у відсотках.
     */
    public int getChargeLevel() {
        return chargeLevel;
    }

    /**
     * Повертає ємність батареї.
     * @return Ємність батареї у mAh.
     */
    public int getCapacity() {
        return capacity;
    }
}
