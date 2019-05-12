package me.azarex.timedtitle.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TitleExecutorService implements Runnable {

    private final World world;
    private final TitleRegistry registry;

    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    public TitleExecutorService(Plugin plugin, World world, TitleRegistry registry) {
        this.world = world;
        this.registry = registry;

        Bukkit.getScheduler().runTaskTimer(plugin, this::run, 0L, 1L);
    }

    @Override
    public void run() {
        ScheduledTitle titleForTick = registry.getTitlesForTick((short)world.getTime());
        System.out.println((short)world.getTime());

        if (titleForTick == null) {
            return;
        }

        world.getPlayers().stream()
                .filter(player -> !player.hasPermission(titleForTick.getPermission()))
                .forEach(titleForTick::displayTitle);
    }
}
