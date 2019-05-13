package me.azarex.timedtitle.scheduler;

import me.azarex.timedtitle.user.User;

/**
 * Stores all information needed for sending a title to the player,
 * should be noted that this is strictly a DTO with a displayTitle method
 */
public class ScheduledTitle {

    private final String title;
    private final String subtitle;
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;
    private final String permission;

    public ScheduledTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut, String permission) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
        this.permission = permission;
    }

    /**
     * Sends the title to the player
     * @param user User which will receive the title
     */
    public void displayTitle(User user) {
        user.getPlayer().sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    /**
     * @return The permission that is required to display the title to the {@link User}
     */
    public String getPermission() {
        return permission;
    }
}
