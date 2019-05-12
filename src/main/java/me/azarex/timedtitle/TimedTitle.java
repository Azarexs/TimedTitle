package me.azarex.timedtitle;

import me.azarex.timedtitle.configuration.ConfigurationService;
import me.azarex.timedtitle.scheduler.TitleExecutorService;
import me.azarex.timedtitle.scheduler.TitleRegistry;
import me.azarex.timedtitle.user.UserLoader;
import me.azarex.timedtitle.user.UserSessionManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TimedTitle extends JavaPlugin {

    private final Path dataFolder = Paths.get(getDataFolder().toPath() + File.separator);

    @Override
    public void onEnable() {
        ConfigurationService<String> configurations = new ConfigurationService<>();

        configurations.register("config", Paths.get(dataFolder + "config.yml"));
        configurations.register("players", Paths.get(dataFolder + "players.yml"));
        configurations.register("lang", Paths.get(dataFolder + "lang.yml"));

        UserLoader userLoader = new UserLoader(configurations.getUnsafe("players"));
        UserSessionManager sessionManager = new UserSessionManager(userLoader);

        final World world = Bukkit.getWorld((String)configurations.getUnsafe("config").get("world"));

        TitleRegistry titleRegistry = new TitleRegistry(configurations.getUnsafe("players"));
        TitleExecutorService titleExecutorService = new TitleExecutorService(this, world, titleRegistry);

        Bukkit.getPluginManager().registerEvents(sessionManager, this);
    }
}
