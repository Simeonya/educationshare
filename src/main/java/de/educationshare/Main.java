package de.educationshare;

import de.educationshare.config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;

@SpringBootApplication
public class Main {
    private final Logger logger = LoggerFactory.getLogger(Main.class);
    private static Main instance;
    private final ConfigManager configManager;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public Main() {
        instance = this;
        configManager = new ConfigManager(Path.of(System.getProperty("user.dir")));

        getLogger().info("ConfigManager loaded");
    }

    /**
     * Get the ConfigManager
     *
     * @return ConfigManager with all configurations
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * Get the instance of the Main class
     *
     * @return Main instance
     */
    public static Main getInstance() {
        return instance;
    }

    /**
     * Get the logger
     *
     * @return SLF4J Logger instance
     */
    public Logger getLogger() {
        return logger;
    }
}