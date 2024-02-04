package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс ConnectionManager является связующим звеном между драйвером БД и библиотекой SQL, который задействуется каждый
 * раз при обращении к нему из DAO-класса. Он не создаёт своего экземпляра, его методы loadDriver и get - статические.
 * Метод loadDriver вызывается при статической инициализации класса и создаёт доступ к драйверу БД. Метод get
 * вызывается из DAO-классов и обращается к библиотеке java.sql, передавая в неё URL, USER и PASSWORD из файла
 * application.properties посредством утилиты PropertiesUtil.
 */
public final class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager() {
    }

    public static Connection get() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
