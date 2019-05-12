package me.azarex.timedtitle.configuration;

import me.azarex.timedtitle.common.Holder;
import me.azarex.timedtitle.common.MutableHolder;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public interface Configuration {

    Path getPath();
    Holder<YamlConfiguration> getYaml();

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

    default void save() {
        try {
            getYaml().get().save(getPath().toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void reload() {
        save();
        getYaml().set(YamlConfiguration.loadConfiguration(getPath().toFile()));
    }

    default boolean contains(String key) {
        return getYaml().get().contains(key);
    }

    default boolean contains(Object key) {
        return contains(key.toString());
    }

    default Object get(String key) {
        return getYaml().get().get(key);
    }

    default boolean getBoolean(String key) {
        return (boolean) getYaml().get().get(key);
    }

    default void set(String key, Object value) {
        getYaml().get().set(key, value);
    }

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
}
