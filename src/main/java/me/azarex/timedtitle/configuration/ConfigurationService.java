package me.azarex.timedtitle.configuration;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationService<T> {

    private final Map<T, Configuration> configurations = new HashMap<>();

    public ConfigurationService<T> register(T key, Path path) {
        return register(key, Configuration.of(path));
    }

    public ConfigurationService<T> register(T key, Configuration configuration) {
        configurations.put(key, configuration);
        return this;
    }

    public Configuration getUnsafe(T key) {
        return configurations.get(key);
    }

}
