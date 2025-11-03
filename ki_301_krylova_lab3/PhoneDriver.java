package ki_301_krylova_lab3;

import java.io.IOException;

/**
 * Клас `PhoneDriver` (Лаб. 3) демонструє використання класу `Smartphone`,
 * який є нащадком абстрактного класу `Phone` та реалізує інтерфейс `Connectivity`.
 */
public class PhoneDriver {
    /**
     * Точка входу в програму. Демонструє поліморфізм та роботу з інтерфейсами.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        
        // Оголошуємо змінну типу Phone, але ініціалізуємо її
        // об'єктом класу Smartphone. Це є поліморфізм.
        Phone myPhone = null;

        try {
            // Створюємо конкретний об'єкт Smartphone
            myPhone = new Smartphone("Pixel 8 Pro", 256000); // 256 GB

            System.out.println("--- STARTING TEST (Lab. 3) ---");
            
            // 1. Тестування абстрактних методів, реалізованих у Smartphone
            myPhone.turnOn();
            myPhone.installApp("Telegram", 600);
            
            // 2. Тестування конкретних методів з Phone
            myPhone.chargeBattery(30);
            myPhone.checkBatteryLevel();
            myPhone.updateOSVersion("14");
            
            // 3. Тестування методів інтерфейсу
            // Щоб викликати методи з Connectivity, нам потрібно
            // перевірити тип і виконати явне приведення типів (casting).
            
            if (myPhone instanceof Connectivity) {
                
                System.out.println("\n--- Testing Connectivity Interface ---");
                Connectivity networkDevice = (Connectivity) myPhone;
                
                networkDevice.connectToWifi("MyHomeNetwork", "password123");
                networkDevice.toggleMobileData(true);
            } else {
                
                System.out.println("This phone does not support Connectivity.");
            }

            // 4. Тестування оновленого методу getInfo
           
            System.out.println("\n--- Final Information ---");
            String info = myPhone.getInfo();
            System.out.println(info);
            
            // 5. Вимкнення
            myPhone.turnOff();
            
            System.out.println("--- TEST COMPLETED ---");

        } catch (IOException e) {
            // Обробка помилок вводу/виводу
            System.err.println("I/O ERROR. Failed to write log to file:");
            e.printStackTrace();
        } finally {
            // Коректне завершення роботи з файлом (вимога завдання)
            // Цей блок завжди виконується.
            if (myPhone != null) {
                
                System.out.println("\nRunning closeLogger()...");
                myPhone.closeLogger();
            }
        }
    }
}
