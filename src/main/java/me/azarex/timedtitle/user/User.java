package me.azarex.timedtitle.user;

import me.azarex.timedtitle.common.Holder;
import org.bukkit.entity.Player;

/**
 * Represents a wrapped {@link Player}, with additional methods to assist in transferring
 * data around
 */
public interface User {

    /**
     * @return The {@link Player} of this {@link User}
     */
    Player getPlayer();

    /**
     * @return True if they want to be sent titles, otherwise false
     */
    Holder<Boolean> isEnabled();

}
