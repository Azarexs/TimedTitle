package me.azarex.timedtitle;

import me.azarex.timedtitle.configuration.ConfigurationService;
import me.azarex.timedtitle.user.UserSessionManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TimedTitle extends JavaPlugin {

    private final Path dataFolder = Paths.get(getDataFolder().toPath() + File.separator);

    @Override
    public void onEnable() {
        ConfigurationService<String> configurationService = new ConfigurationService<>();

        configurationService.register("config", Paths.get(dataFolder + "config.yml"));
        configurationService.register("players", Paths.get(dataFolder + "players.yml"));
        configurationService.register("lang", Paths.get(dataFolder + "lang.yml"));

        UserSessionManager sessionManager = new UserSessionManager(configurationService.getUnsafe("players"));

        Bukkit.getPluginManager().registerEvents(sessionManager, this);
    }
}
