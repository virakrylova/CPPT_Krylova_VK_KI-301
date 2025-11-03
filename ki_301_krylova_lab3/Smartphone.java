package ki_301_krylova_lab3;

import java.io.IOException;

/**
 * Клас Smartphone розширює абстрактний клас Phone і реалізує інтерфейс Connectivity.
 * Представляє конкретну реалізацію телефону - смартфон.
 */
public class Smartphone extends Phone implements Connectivity {

    private String currentWifiNetwork;
    private boolean isMobileDataEnabled;

    /**
     * Конструктор для створення Смартфону.
     *
     * @param name Назва смартфону (наприклад, "Samsung Galaxy").
     * @param storage Доступне сховище в MB.
     * @throws IOException якщо виникає помилка при ініціалізації логера.
     */
    public Smartphone(String name, int storage) throws IOException {
        // 1. Виклик конструктора батьківського (абстрактного) класу
        super(name, storage); 
        
        // Ініціалізація власних полів
        this.currentWifiNetwork = "None";
        this.isMobileDataEnabled = false;
        
        // Використовуємо логер, отриманий від батьківського класу
        
        getLogger().log(String.format("Smartphone created: %s", name));
    }

    // --- Реалізація Абстрактних Методів ---

    /**
     * {@inheritDoc}
     * Реалізує увімкнення смартфону, імітуючи завантаження ОС.
     */
    @Override
    public void turnOn() throws IOException {
        setIsOn(true); // Використовуємо protected сетер
        String osName = getOs().getName(); // Використовуємо protected гетер
        
        getLogger().log(String.format("Smartphone %s is turning on... Booting %s...", getName(), osName));
        System.out.printf("Smartphone %s is turning on... Booting %s...\n", getName(), osName);
    }

    /**
     * {@inheritDoc}
     * Реалізує встановлення програми, перевіряючи сховище та ОС.
     */
    @Override
    public void installApp(String appName, int appSize) throws IOException {
        if (!getIsOn()) {
             
             getLogger().log(String.format("Failed to install %s. Smartphone is off.", appName));
             System.out.println("Failed to install app. Smartphone is off.");
             return;
        }
        
        // Перевірка сумісності (проста імітація)
        if (!getOs().getName().equals("Android")) {
             
             getLogger().log(String.format("Failed to install %s. Incompatible OS: %s.", appName, getOs().getName()));
             System.out.println("Application is not compatible with your OS.");
             return;
        }

        // Перевірка сховища (логіка з батьківського класу)
        if (appSize <= getAvailableStorage()) {
            getInstalledApps().add(appName);
            setAvailableStorage(getAvailableStorage() - appSize);
            
            
            String logMsg = String.format("Installed app: %s (size: %d MB). Storage remaining: %d MB", appName, appSize, getAvailableStorage());
            getLogger().log(logMsg);
            System.out.println(logMsg);
        } else {
            
            String logMsg = String.format("Failed to install %s. Not enough storage. Required: %d MB, Available: %d MB.", appName, appSize, getAvailableStorage());
            getLogger().log(logMsg);
            System.out.println(logMsg);
        }
    }

    /**
     * {@inheritDoc}
     * Повертає детальну інформацію про смартфон.
     */
    @Override
    public String getInfo() throws IOException {
        // Використовуємо protected метод з Phone для базової інформації
        String basicInfo = super.getInfoString(); 
       
        String smartphoneInfo = String.format("[Smartphone] %s | Wi-Fi: %s | Mobile Data: %s", 
                                       basicInfo, currentWifiNetwork, isMobileDataEnabled ? "On" : "Off");
        
    
        getLogger().log("Retrieved full smartphone information.");
        System.out.println("Retrieved full smartphone information.");
        return smartphoneInfo;
    }

    // --- Реалізація Методів Інтерфейсу Connectivity ---

    /**
     * {@inheritDoc}
     */
    @Override
    public void connectToWifi(String ssid, String password) throws IOException {
        // Імітація підключення
        this.currentWifiNetwork = ssid;

        String logMsg = String.format("Connected to Wi-Fi network: %s", ssid);
        getLogger().log(logMsg);
        System.out.println(logMsg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleMobileData(boolean enable) throws IOException {
        this.isMobileDataEnabled = enable;

        String state = enable ? "enabled" : "disabled";
        String logMsg = String.format("Mobile data (5G) %s", state);
        getLogger().log(logMsg);
        System.out.println(logMsg);
    }
}
