package me.azarex.timedtitle.utility;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Utility class for coloring {@link String}s and {@link List<String>}s
 */
public class Colors {

    private static final UnaryOperator<String> color = message ->
            ChatColor.translateAlternateColorCodes('&', message);

    private Colors() {
        throw new UnsupportedOperationException("This is an utility class, and thus cannot be instantiated!");
    }

    /**
     * Formats a string for color, translates '&' symbols into section sign symbol
     * @param message Message to be formatted
     * @return Message, after it has been formatted
     */
    public static String color(String message) {
        return color.apply(message);
    }

    /**
     * Formats a whole list for color
     * @param list List to be formatted
     */
    public static void color(List<String> list) {
        list.replaceAll(color);
    }

}
