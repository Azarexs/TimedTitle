package me.azarex.timedtitle.scheduler;

import org.bukkit.entity.Player;

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

    public void displayTitle(Player player) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    public String getPermission() {
        return permission;
    }
}
