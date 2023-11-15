package Singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Singleton<T extends Singleton<T>> {
    private static final Map<Class<? extends Singleton>, Singleton> instances = new ConcurrentHashMap<>();

    protected Singleton() {
        // protected constructor to prevent instantiation
    }

    public static <T extends Singleton<T>> T getIns(Class<T> type) {
        instances.computeIfAbsent(type, key -> {
            try {
                return type.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Failed to create an instance of " + type, e);
            }
        });
        return type.cast(instances.get(type));
    }
}
