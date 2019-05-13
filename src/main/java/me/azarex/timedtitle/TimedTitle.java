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
import java.nio.file.Paths;

public final class TimedTitle extends JavaPlugin {

    @Override
    public void onEnable() {
        final ConfigurationService<String> configurations = new ConfigurationService<>();

        configurations.register("config", Paths.get(getDataFolder().toPath() + File.separator + "config.yml"));
        configurations.register("players", Paths.get(getDataFolder().toPath() + File.separator + "players.yml"));
        configurations.register("lang", Paths.get(getDataFolder().toPath() + File.separator + "lang.yml"));

        final UserLoader userLoader = new UserLoader(configurations.getUnsafe("players"));
        final UserSessionManager sessionManager = new UserSessionManager(userLoader);

        final World world = Bukkit.getWorld((String) configurations.getUnsafe("config").get("world"));

        final TitleRegistry titleRegistry = new TitleRegistry(configurations.getUnsafe("players"));
        final TitleExecutorService titleExecutorService = new TitleExecutorService(world, titleRegistry, userLoader);

        Bukkit.getScheduler().runTaskTimer(this, titleExecutorService, 0L, 1L);
        Bukkit.getPluginManager().registerEvents(sessionManager, this);
    }
}
