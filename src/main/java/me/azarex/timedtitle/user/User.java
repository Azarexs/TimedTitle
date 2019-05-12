package me.azarex.timedtitle.user;

import me.azarex.timedtitle.common.Holder;
import org.bukkit.entity.Player;

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
