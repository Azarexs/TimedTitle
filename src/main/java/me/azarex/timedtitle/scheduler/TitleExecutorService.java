package me.azarex.timedtitle.scheduler;

import me.azarex.timedtitle.user.UserLoader;
import org.bukkit.World;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Executes and sends {@link ScheduledTitle} to all online {@link me.azarex.timedtitle.user.User}'s
 */
public class TitleExecutorService implements Runnable {

    private final World world;
    private final TitleRegistry titleRegistry;
    private final UserLoader userLoader;

    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    public TitleExecutorService(World world, TitleRegistry titleRegistry, UserLoader userLoader) {
        this.world = world;
        this.titleRegistry = titleRegistry;
        this.userLoader = userLoader;
    }

    @Override
    public void run() {
        ScheduledTitle titleForTick = titleRegistry.getTitlesForTick((short) world.getTime());

        if (titleForTick == null) {
            return;
        }

        world.getPlayers().stream()
                .map(userLoader::getUser)
                .filter(user -> user.isEnabled().get())
                .filter(user -> !user.getPlayer().hasPermission(titleForTick.getPermission()))
                .forEach(titleForTick::displayTitle);
    }
}
