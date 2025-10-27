package ki_301_krylova_lab3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактний клас Phone, що описує базову функціональність телефону.
 * Цей клас служить суперкласом для конкретних типів телефонів (наприклад, Smartphone).
 * (Модифіковано з Лаб. 2)
 */
public abstract class Phone {

    // Поля залишаються private, але доступні через protected гетери для нащадків
    private Battery battery;
    private Screen screen;
    private OperatingSystem operatingSystem;
    private Logger logger;
    private String name;
    private boolean isOn;
    private List<String> installedApps;
    private int availableStorage;

    /**
     * Конструктор для абстрактного класу Phone.
     * Ініціалізує основні компоненти.
     *
     * @param name Назва телефону.
     * @param storage Доступне сховище в MB.
     * @throws IOException якщо виникає помилка при ініціалізації логера.
     */
    public Phone(String name, int storage) throws IOException {
        this.name = name;
        this.isOn = false;
        this.installedApps = new ArrayList<>();
        this.availableStorage = storage;
        
        // Ініціалізація компонентів за замовчуванням
        this.battery = new Battery(80, 4500); // Початковий заряд 80%
        this.screen = new Screen(6.1, "OLED");
        this.operatingSystem = new OperatingSystem("Android", "13");

        // Ініціалізація логера
        this.logger = new Logger("phone_log.txt");
        // ПЕРЕКЛАДЕНО:
        logger.log(String.format("Abstract Phone '%s' created. Storage: %d MB", name, storage));
    }
    
    // --- Абстрактні Методи (мають бути реалізовані нащадками) ---

    /**
     * Вмикає телефон. Логіка увімкнення (наприклад, завантаження ОС)
     * залежить від конкретної реалізації телефону.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public abstract void turnOn() throws IOException;

    /**
     * Встановлює нову програму. Логіка встановлення (наприклад, перевірка
     * сумісності) залежить від конкретної реалізації.
     * @param appName Назва програми.
     * @param appSize Розмір програми в MB.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public abstract void installApp(String appName, int appSize) throws IOException;

    /**
     * Повертає повну інформацію про пристрій.
     * @return Рядок з детальною інформацією.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public abstract String getInfo() throws IOException;


    // --- Конкретні Методи (успадковуються як є) ---

    /**
     * Вимикає телефон.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void turnOff() throws IOException {
        isOn = false;
        // ПЕРЕКЛАДЕНО:
        logger.log(String.format("Phone %s turned off", name));
        System.out.printf("Phone %s turned off\n", name);
    }

    /**
     * Заряджає батарею телефону.
     * @param minutes Кількість хвилин зарядки.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void chargeBattery(int minutes) throws IOException {
        int oldCharge = battery.getChargeLevel();
        battery.charge(minutes);
        // ПЕРЕКЛАДЕНО:
        logger.log(String.format("Battery charged from %s%% to %s%%", oldCharge, battery.getChargeLevel()));
        System.out.printf("Battery charged from %s%% to %s%%\n", oldCharge, battery.getChargeLevel());
    }

    /**
     * Змінює яскравість екрану.
     * @param brightness Нове значення яскравості (0-100).
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void changeScreenBrightness(int brightness) throws IOException {
        screen.setBrightness(brightness);
        // ПЕРЕКЛАДЕНО:
        logger.log(String.format("Screen brightness changed to %s", brightness));
        System.out.printf("Screen brightness changed to %s\n", brightness);
    }
    
    /**
     * Оновлює версію операційної системи.
     * @param newVersion Нова версія ОС.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void updateOSVersion(String newVersion) throws IOException {
        String oldVersion = operatingSystem.getVersion();
        operatingSystem.setVersion(newVersion);
        // ПЕРЕКЛАДЕНО:
        logger.log(String.format("OS updated from version %s to %s", oldVersion, newVersion));
        System.out.printf("OS updated from version %s to %s\n", oldVersion, newVersion);
    }

    /**
     * Видаляє програму з телефону.
     * @param appName Назва програми.
     * @param appSize Розмір програми в MB.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public void uninstallApp(String appName, int appSize) throws IOException {
        if (installedApps.remove(appName)) {
            availableStorage += appSize;
            // ПЕРЕКЛАДЕНО:
            logger.log(String.format("Uninstalled app: %s (freed %d MB). Available: %d MB", appName, appSize, availableStorage));
            System.out.printf("Uninstalled app: %s (freed %d MB). Available: %d MB\n", appName, appSize, availableStorage);
        } else {
            // ПЕРЕКЛАДЕНО:
            logger.log(String.format("Failed to uninstall app %s. App not found.", appName));
            System.out.printf("Failed to uninstall app %s. App not found.\n", appName);
        }
    }
    
    /**
     * Перевіряє рівень заряду батареї.
     * @return Рівень заряду у відсотках.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    public int checkBatteryLevel() throws IOException {
        int level = battery.getChargeLevel();
        // ПЕРЕКЛАДЕНО:
        logger.log(String.format("Checked battery level: %s%%", level));
        System.out.printf("Checked battery level: %s%%\n", level);
        return level;
    }

    /**
     * Закриває логер для коректного збереження даних у файл.
     */
    public void closeLogger() {
        logger.close();
        // ПЕРЕКЛАДЕНО:
        System.out.println("Log file closed.");
    }
    
    // --- Protected Гетери (для доступу нащадків) ---
    
    protected Logger getLogger() { return logger; }
    protected String getName() { return name; }
    protected boolean getIsOn() { return isOn; }
    protected void setIsOn(boolean isOn) { this.isOn = isOn; }
    protected OperatingSystem getOs() { return operatingSystem; }
    protected int getAvailableStorage() { return availableStorage; }
    protected void setAvailableStorage(int storage) { this.availableStorage = storage; }
    protected List<String> getInstalledApps() { return installedApps; }
    
    /**
     * Повертає базовий рядок інформації про телефон.
     * @return Рядок з інформацією.
     */
    protected String getInfoString() {
         // ПЕРЕКЛАДЕНО:
         return String.format("Name: %s | OS: %s %s | Battery: %d%%/%dmAh | Storage: %d MB",
                name, operatingSystem.getName(), operatingSystem.getVersion(),
                battery.getChargeLevel(), battery.getCapacity(), availableStorage);
    }
}
