package org.nala.airframe.core.store;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Provides dependencies.
 */
public class DependencyStore {
    /**
     * The {@link HashMap} that stores instantiated {@link Object} keyed by their {@link Class} type.
     */
    private final HashMap<Class<?>, Object> dependencies = new HashMap<>();
    /**
     * The {@link DependencyStore}'s instance.
     */
    private static final DependencyStore instance = new DependencyStore();

    /**
     * Private Constructor.
     */
    private DependencyStore() {
        // Prevents initialization
    }

    /**
     * Returns the singleton {@link DependencyStore}.
     *
     * @return a {@link DependencyStore}
     */
    public static DependencyStore getStore() {
        return instance;
    }

    /**
     * Returns the required dependency.
     *
     * @param type a {@link Class}
     * @return the singleton {@link Object}
     */
    public Object getOrLoad(Class<?> type) {
        // Idea: non-singletons
        if (!dependencies.containsKey(type)) {
            dependencies.put(type, getNewDependency(type));
        }
        return dependencies.get(type);
    }

    /**
     * Recursively instantiates a dependency and its own dependencies.
     *
     * @param type the {@link Class}
     * @return the instantiated {@link Object}
     */
    private Object getNewDependency(Class<?> type) {
        // Todo: detect cycles
        try {
            Constructor<?> constructor = type.getConstructors()[0];
            Object[] params = Arrays.stream(constructor.getParameterTypes()).map(this::getOrLoad).toArray();
            return constructor.newInstance(params);
        } catch (Exception e) { // Todo: error handling / descriptive errors
            System.err.println("One day, I will be a useful message");
            throw new RuntimeException();
        }
    }
}
