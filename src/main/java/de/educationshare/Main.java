package de.educationshare;

import de.educationshare.config.ConfigManager;
import de.educationshare.database.HibernateUtil;
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
    private final HibernateUtil hibernateUtil;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public Main() {
        instance = this;
        configManager = new ConfigManager(Path.of(System.getProperty("user.dir")));
        configManager.init();
        getLogger().info("ConfigManager loaded");

        hibernateUtil = new HibernateUtil(configManager.getDatabaseConfig().getHost(), configManager.getDatabaseConfig().getPort(), configManager.getDatabaseConfig().getName(), configManager.getDatabaseConfig().getUser(), configManager.getDatabaseConfig().getPassword());
        getHibernateUtil().buildSessionFactory();
        getLogger().info("HibernateUtil loaded");
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

    /**
     * Get the HibernateUtil where you can access the Database
     *
     * @return Hibernate instance
     */
    public HibernateUtil getHibernateUtil() {
        return hibernateUtil;
    }
}