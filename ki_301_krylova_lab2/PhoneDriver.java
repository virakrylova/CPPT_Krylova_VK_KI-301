package ki_301_krylova_lab2;

import java.io.IOException;

/**
 * Клас `PhoneDriver` містить метод `main`, який демонструє використання класу `Phone`.
 * Створюється екземпляр телефону, викликаються методи для керування його станом,
 * заряджання, встановлення та видалення додатків, а також інші функції.
 */
public class PhoneDriver {
    /**
     * Точка входу в програму. Демонструє роботу з телефоном шляхом виклику різних методів.
     *
     * @param args Аргументи командного рядка.
     */
    public static void main(String[] args) {
        try {
            Phone phone = new Phone("Iphone", 10000);

            phone.turnOn();
            phone.chargeBattery(3);
            phone.changeScreenBrightness(80);
            phone.updateOSVersion("19");
            phone.checkBatteryLevel();
            phone.installApp("Telegram", 400);
            phone.uninstallApp("Telegram", 400);
            phone.checkAvailableStorage();
            phone.getInfo();
            phone.isPhoneOn();
            phone.turnOff();

            phone.closeLogger();
        } catch (IOException e) {
            // Обробка помилок, що виникають під час запису в файл
            throw new RuntimeException("An error occurred while writing to the file:" + e.getMessage());
        }
    }
}