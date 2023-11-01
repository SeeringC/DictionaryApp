package Singleton;

public class Singleton<T extends Singleton<T>> {
    private static volatile Singleton instance;

    protected Singleton() {
        // protected constructor to prevent instantiation
    }

    public static <T extends Singleton<T>> T getIns(Class<T> type) {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    try {
                        instance = type.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException("Failed to create an instance of " + type, e);
                    }
                }
            }
        }
        return type.cast(instance);
    }
}
