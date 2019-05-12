package me.azarex.timedtitle.scheduler;

import me.azarex.timedtitle.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

public class TitleRegistry {

    private Map<Short, ScheduledTitle> titles = new HashMap<>();

    public TitleRegistry(Configuration configuration) {
        final YamlConfiguration yaml = configuration.getYaml().get();

        yaml.getValues(false)
                .forEach((key, $) -> {
                    final String title = yaml.getString(key + ".title");
                    final String subtitle = yaml.getString(key + ".subtitle");
                    final String permission = key;

                    final int fadeIn = yaml.getInt(key + ".fadeIn");
                    final int stay = yaml.getInt(key + ".stay");
                    final int fadeOut = yaml.getInt(key + ".fadeOut");
                    final short time = (short) yaml.getInt(key + ".tick-count");

                    titles.put(time, new ScheduledTitle(title, subtitle, fadeIn, stay, fadeOut, permission));
                });
    }

    public ScheduledTitle getTitlesForTick(short tick) {
        return titles.get(tick);
    }

}
