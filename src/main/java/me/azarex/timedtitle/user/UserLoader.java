package me.azarex.timedtitle.user;

import me.azarex.timedtitle.common.Holder;
import me.azarex.timedtitle.common.MutableHolder;
import me.azarex.timedtitle.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.IdentityHashMap;
import java.util.Map;

import static java.lang.String.format;

class UserLoader {

    private final Map<Player, User> users = new IdentityHashMap<>();
    private final Configuration configuration;

    private static final String ENABLED_PATH = "%s.enabled";

    UserLoader(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Loads {@link User}'s properties from a {@link Configuration}
     * @param player Player to be loaded from the {@link Configuration}
     */
    void load(Player player) {
        if (!configuration.contains(player.getUniqueId())) {
            configuration.set(format(ENABLED_PATH, player.getUniqueId()), true);
        }

        final Holder<Boolean> isEnabled = new MutableHolder<>((boolean) configuration.get(player.getUniqueId().toString() + ".enabled"));

        users.put(player, new User() {
            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public Holder<Boolean> isEnabled() {
                return isEnabled;
            }
        });
    }

    /**
     * Unloads and saves {@link User}'s properties to a {@link Configuration}
     * @param player Player to be saved to the {@link Configuration}
     */
    void unload(Player player) {
        User user = users.get(player);

        configuration.set(format(ENABLED_PATH, player.getUniqueId()), user.isEnabled().get());

        users.remove(player);
    }


}
