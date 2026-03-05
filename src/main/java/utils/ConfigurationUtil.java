package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurationUtil {

    private static final Properties PROPS = new Properties();
    private static volatile boolean initialized = false;

  //  private ConfigurationUtil() { }

    /** Lazy init – loads /config.properties from classpath if present. */
    private static void initIfNeeded() {
        if (initialized) return;
        synchronized (ConfigurationUtil.class) {
            if (initialized) return;
            try (InputStream in = ConfigurationUtil.class.getResourceAsStream("/config.properties")) {
                if (in != null) {
                    PROPS.load(in);
                }
            } catch (IOException ignored) {
                // No config file or unreadable – we rely on defaults/system props
            }
            initialized = true;
        }
    }

    public static String get(String key) {
        initIfNeeded();
        String sys = System.getProperty(key);
        if (sys != null) return sys;
        return PROPS.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        String v = get(key);
        return (v == null || v.isEmpty()) ? defaultValue : v;
    }

    public static int getInt(String key, int defaultValue) {
        String v = get(key);
        if (v == null) return defaultValue;
        try { return Integer.parseInt(v.trim()); } catch (Exception e) { return defaultValue; }
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String v = get(key);
        if (v == null) return defaultValue;
        return Boolean.parseBoolean(v.trim());
    }

    public static double getDouble(String key, double defaultValue) {
        String v = get(key);
        if (v == null) return defaultValue;
        try { return Double.parseDouble(v.trim()); } catch (Exception e) { return defaultValue; }
    }
}