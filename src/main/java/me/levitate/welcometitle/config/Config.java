package me.levitate.welcometitle.config;

import de.exlll.configlib.Comment;
import lombok.Getter;
import de.exlll.configlib.Configuration;

@Getter
@Configuration
@SuppressWarnings("FieldMayBeFinal")
public class Config {
    @Comment("The title and subtitle of the welcome message.")
    private String title = "<green><bold>Welcome to the server!";
    private String subtitle = "";

    @Comment("Durations are in milliseconds.")
    private int fadeInDuration = 500;
    private int stayDuration = 3000;
    private int fadeOutDuration = 2500;
}
