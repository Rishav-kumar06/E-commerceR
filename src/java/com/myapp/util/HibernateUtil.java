package com.myapp.util;

/**
 * Placeholder HibernateUtil kept for compatibility. Hibernate has been removed.
 * Any attempt to use it will result in an UnsupportedOperationException.
 */
public final class HibernateUtil {
    private HibernateUtil() { }

    public static Object getSessionFactory() {
        throw new UnsupportedOperationException("Hibernate has been removed. Use com.myapp.util.DBConnectionUtil for JDBC connections.");
    }

    public static void shutdown() {
        // No-op
    }
}