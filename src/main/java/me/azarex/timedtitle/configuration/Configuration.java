package me.azarex.timedtitle.configuration;

import me.azarex.timedtitle.common.Holder;
import me.azarex.timedtitle.common.MutableHolder;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public interface Configuration {

    static Configuration of(Path path) {
        Holder<YamlConfiguration> yaml = new MutableHolder<>();

        return new Configuration() {
            @Override
            public Path getPath() {
                return path;
            }

            @Override
            public Holder<YamlConfiguration> getYaml() {
                return yaml;
            }
        }.create();
    }

    Path getPath();
    Holder<YamlConfiguration> getYaml();

    /**
     * Creates the file if it doesn't exist, and copies the resource from the resource folder,
     * then registers the {@link YamlConfiguration} to load the specified {@link Path}
     * @return Instance of this class, for chaining purposes
     */
    default Configuration create() {
        if (Files.notExists(getPath())) {
            try {
                if (Files.notExists(getPath().getParent())) {
                    Files.createDirectories(getPath().getParent());
                }

                Files.createFile(getPath());
                Files.copy(getClass().getResourceAsStream("/" + getPath().toFile().getName()), getPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        getYaml().set(YamlConfiguration.loadConfiguration(getPath().toFile()));
        return this;
    }

    /**
     * Saves the {@link YamlConfiguration} with the specified {@link Path}
     */
    default void save() {
        try {
            getYaml().get().save(getPath().toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves and reloads the {@link YamlConfiguration} with the specified {@link Path}
     */
    default void reload() {
        save();
        getYaml().set(YamlConfiguration.loadConfiguration(getPath().toFile()));
    }

    /**
     * @param key Key to be associated path inside of the {@link YamlConfiguration}
     * @return true if the configuration can find it, false otherwise
     */
    default boolean contains(String key) {
        return getYaml().get().contains(key);
    }

    /**
     * @param key Key to be associated path inside of the {@link YamlConfiguration}
     * @return true if the configuration can find it, false otherwise
     */
    default boolean contains(Object key) {
        return contains(key.toString());
    }

    /**
     * @param key Key specified with a value in the {@link YamlConfiguration}
     * @return The value associated with the key
     */
    default Object get(String key) {
        return getYaml().get().get(key);
    }

    /**
     * @param key Key specified with a value in the {@link YamlConfiguration}
     * @return The value associated with the key
     */
    default boolean getBoolean(String key) {
        return (boolean) getYaml().get().get(key);
    }

    /**
     * @param key Key associated to the value
     * @param value Value associated to the key
     */
    default void set(String key, Object value) {
        getYaml().get().set(key, value);
    }
}
