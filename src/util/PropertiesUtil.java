package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс PropertiesUtil - служебный класс, служащий для подключения приложения к базе данных. Класс реализует паттерн
 * Синглтон, чтобы гарантировать только одно подключение к базе данных. В классе реализованы два метода: метод
 * loadProperties осуществляет получение свойств базы данных из файла application.properties и записывает их в
 * единственный экземпляр класса Properties для дальнейшей передачи с использованием метода get в класс
 * ConnectionManager.
 */
public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
