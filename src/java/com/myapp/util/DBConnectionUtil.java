package com.myapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;

public class DBConnectionUtil {

    private static final String PROPS = "com/myapp/util/db.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream in = DBConnectionUtil.class.getClassLoader().getResourceAsStream(PROPS)) {
            if (in == null) throw new RuntimeException("db.properties not found on classpath: " + PROPS);
            Properties p = new Properties();
            p.load(in);
            url = p.getProperty("db.url");
            user = p.getProperty("db.user");
            password = p.getProperty("db.password");
            String driver = p.getProperty("db.driver");
            if (driver != null && !driver.isEmpty()) {
                try {
                    Class.forName(driver);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("JDBC Driver class not found: " + driver, e);
                }
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeResources(AutoCloseable... resources) {
        if (resources == null) return;
        for (AutoCloseable r : resources) {
            if (r != null) {
                try { r.close(); } catch (Exception ignored) { }
            }
        }
    }
}
