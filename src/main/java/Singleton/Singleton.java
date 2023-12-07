package Singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Singleton<T extends Singleton<T>> {
    // A map to hold instances of singleton classes
    // ConcurrentHashMap is used to ensure thread safety.
    private static final Map<Class<? extends Singleton>, Singleton> instances = new ConcurrentHashMap<>();

    protected Singleton() {
        // protected constructor to prevent instantiation
    }

    // Method to get the singleton instance of a class.
    public static <T extends Singleton<T>> T getIns(Class<T> type) {
        // Compute the instance if absent, otherwise return the existing one.
        instances.computeIfAbsent(type, key -> {
            try {
                // Attempt to create a new instance of the class.
                return type.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Failed to create an instance of " + type, e);
            }
        });
        // Cast and return the instance from the map.
        return type.cast(instances.get(type));
    }
}
