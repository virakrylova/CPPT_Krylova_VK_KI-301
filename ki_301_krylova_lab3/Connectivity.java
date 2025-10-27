package ki_301_krylova_lab3;

import java.io.IOException;

/**
 * Інтерфейс Connectivity визначає методи для підключення до мереж.
 * Класи, що реалізують цей інтерфейс, повинні надавати
 * реалізацію для підключення до Wi-Fi та мобільного інтернету.
 */
public interface Connectivity {
    
    /**
     * Підключається до мережі Wi-Fi.
     *
     * @param ssid Назва мережі Wi-Fi.
     * @param password Пароль до мережі.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    void connectToWifi(String ssid, String password) throws IOException;

    /**
     * Вмикає або вимикає мобільний інтернет (4G/5G).
     *
     * @param enable true, щоб увімкнути, false - щоб вимкнути.
     * @throws IOException якщо виникає помилка при записі в лог.
     */
    void toggleMobileData(boolean enable) throws IOException;
}
