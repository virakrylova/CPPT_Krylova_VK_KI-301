package ki_301_krylova_lab2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє телефон з основними функціями, такими як увімкнення, вимкнення,
 * встановлення додатків, зарядка батареї тощо.
 */
public class Phone {
    private Battery battery;
    private Screen screen;
    private OperatingSystem operatingSystem;
    private Logger logger;
    private String name;
    private boolean isOn;
    private List<String> installedApps;
    private int availableStorage;

    /**
     * Конструктор для створення телефону з іменем та доступним сховищем.
     * @param name Назва телефону.
     * @param storage Доступне сховище в MB.
     * @throws IOException якщо виникає помилка при ініціалізації логера.
     */
    public Phone(String name, int storage) throws IOException {
        this.name = name;
        this.isOn = false;
        this.installedApps = new ArrayList<>();
        this.availableStorage = storage;
        this.battery = new Battery(100, 100);
        this.screen = new Screen(6.2, "Super Retina XDR OLED");
        this.operatingSystem = new OperatingSystem("IOS", "18");

        this.logger = new Logger("phone_log.txt");
        logger.log(String.format("Phone %s created.", this.toString()));
    }

    /**
     * Конструктор для створення телефону з наданими параметрами.
     * @param battery Батарея телефону.
     * @param screen Екран телефону.
     * @param operatingSystem Операційна система телефону.
     * @param name Назва телефону.
     * @param storage Доступне сховище в MB.
     * @throws IOException якщо виникає помилка при ініціалізації логера.
     */
    public Phone(Battery battery, Screen screen, OperatingSystem operatingSystem, String name, int storage) throws IOException {
        this.battery = battery;
        this.screen = screen;
        this.operatingSystem = operatingSystem;
        this.name = name;
        this.isOn = false;
        this.installedApps = new ArrayList<>();
        this.availableStorage = storage;

        this.logger = new Logger("phone_log.txt");
        logger.log(String.format("Phone %s created.", this.toString()));
    }

    /**
     * Вмикає телефон.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void turnOn() throws IOException {
        isOn = true;
        logger.log(String.format("Phone %s is on", name));
        System.out.printf("Phone %s is on\n", name);
    }

    /**
     * Вимикає телефон.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void turnOff() throws IOException {
        isOn = false;
        logger.log(String.format("Phone %s is  off", name));
        System.out.printf("Phone %s is off\n", name);
    }

    /**
     * Заряджає батарею телефону.
     * @param minutes Кількість хвилин зарядки.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void chargeBattery(int minutes) throws IOException {
        int oldCharge = battery.getChargeLevel();
        battery.charge(minutes);
        logger.log(String.format("Battery charged from %s to %s", oldCharge, battery.getChargeLevel()));
        System.out.printf("Battery charged from %s to %s\n", oldCharge, battery.getChargeLevel());
    }

    /**
     * Змінює яскравість екрану.
     * @param brightness Нове значення яскравості (0-100).
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void changeScreenBrightness(int brightness) throws IOException {
        screen.setBrightness(brightness);
        logger.log(String.format("Changed screen brightness to %s", brightness));
        System.out.printf("Changed screen brightness to %s\n", brightness);
    }

    /**
     * Оновлює версію операційної системи.
     * @param newVersion Нова версія ОС.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void updateOSVersion(String newVersion) throws IOException {
        String oldVersion = operatingSystem.getVersion();
        operatingSystem.setVersion(newVersion);
        logger.log(String.format("Updated OS from version %s to %s", oldVersion, newVersion));
        System.out.printf("Updated OS from version %s to %s\n", oldVersion, newVersion);
    }

    /**
     * Перевіряє рівень заряду батареї.
     * @return Рівень заряду батареї у відсотках.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public int checkBatteryLevel() throws IOException {
        int level = battery.getChargeLevel();
        logger.log(String.format("Battery level checked: %s", level));
        System.out.printf("Battery level checked: %s\n", level);
        return level;
    }

    /**
     * Встановлює нову програму на телефон.
     * @param appName Назва програми для встановлення.
     * @param appSize Розмір програми в MB.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void installApp(String appName, int appSize) throws IOException {
        if (appSize <= availableStorage) {
            installedApps.add(appName);
            availableStorage -= appSize;
            logger.log(String.format("New program installed: %s (size: %d MB)", appName, appSize));
            System.out.printf("New program installed: %s (size: %d MB)\n", appName, appSize);
        } else {
            logger.log(String.format("Failed to install %s. Not enough space..", appName));
            System.out.printf("Failed to install %s. Not enough space..\n", appName);
        }
    }

    /**
     * Видаляє програму з телефону.
     * @param appName Назва програми для видалення.
     * @param appSize Розмір програми в MB.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void uninstallApp(String appName, int appSize) throws IOException {
        if (installedApps.remove(appName)) {
            availableStorage += appSize;
            logger.log(String.format("Uninstalled program: %s (%d MB freed up)", appName, appSize));
            System.out.printf("Uninstalled program: %s (%d MB freed up)\n", appName, appSize);
        } else {
            logger.log(String.format("Could not uninstall the program %s. Program not found.", appName));
            System.out.printf("Could not uninstall the program %s. Program not found.\n", appName);
        }
    }

    /**
     * Перевіряє доступне місце на телефоні.
     * @return Доступне місце в MB.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public int checkAvailableStorage() throws IOException {
        logger.log(String.format("Checked available space: %d MB", availableStorage));
        System.out.printf("Checked available space: %d MB\n", availableStorage);
        return availableStorage;
    }

    /**
     * Перевіряє, чи ввімкнений телефон.
     * @return true, якщо телефон ввімкнений, false - якщо вимкнений.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public boolean isPhoneOn() throws IOException {
        logger.log(String.format("Phone status checked: %s", isOn ? "on" : "off"));
        System.out.printf("Phone status checked: %s\n", isOn ? "on" : "off");
        return isOn;
    }

    /**
     * Отримує інформацію про телефон.
     * @return Рядок з інформацією про телефон.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public String getInfo() throws IOException {
        String info = "Phone: " + name + ", ОС: " + operatingSystem.getName() + " " + operatingSystem.getVersion()
                + ", Screen: " + screen.getSize() + "\" " + screen.getType()
                + ", Battery: " + battery.getCapacity() + "mAh";

        logger.log("Phone information received");
        System.out.println("Phone information received");
        return info;
    }

    /**
     * Закриває логер для збереження даних у файл.
     *
     * @throws IOException якщо виникає помилка під час закриття логера.
     */
    public void closeLogger() throws IOException {
        logger.close();
    }
}
