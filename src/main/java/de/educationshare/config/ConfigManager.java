package de.educationshare.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.educationshare.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.chrono.MinguoChronology;

public class ConfigManager {
    /**
     * Database Configuration File
     */
    private final File databaseFile;

    /**
     * Database Configuration
     */
    private DatabaseConfig databaseConfig;

    /**
     * Create a new ConfigManager
     *
     * @param dataDirectory Where the configuration files are stored
     */
    public ConfigManager(Path dataDirectory) {
        dataDirectory = Path.of(dataDirectory + "/config");
        File dataDir = dataDirectory.toFile();
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        Main.getInstance().getLogger().info("Data Directory: " + dataDir.getAbsolutePath());
        databaseFile = new File(dataDir, "database.json");

        if (!databaseFile.exists()) {
            try {
                databaseFile.createNewFile();
            } catch (IOException e) {
                Main.getInstance().getLogger().error("An error occurred while creating the database configuration file", e);
            }
        }
    }

    /**
     * Load the Database Configuration
     *
     * @return Database Configuration
     * @throws IOException If an error occurred while reading the file
     */
    public DatabaseConfig loadDatabaseConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (!databaseFile.exists() || databaseFile.length() == 0) {
            return new DatabaseConfig();
        }
        return mapper.readValue(databaseFile, DatabaseConfig.class);
    }

    /**
     * Save the Database Configuration
     *
     * @param config Database Configuration
     * @throws IOException If an error occurred while writing the file
     */
    public void saveDatabaseConfig(DatabaseConfig config) throws IOException {
        Main.getInstance().getLogger().info("Saving Database Configuration");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ObjectNode rootNode = mapper.valueToTree(config);
        mapper.writeValue(databaseFile, rootNode);
        Main.getInstance().getLogger().info("Database Configuration saved");
    }

    /**
     * Initialize the ConfigManager
     * Load the Configuration Files
     */
    public void init() {
        try {
            Main.getInstance().getLogger().info("Loading Database Configuration");
            setDatabaseConfig(loadDatabaseConfig());
            Main.getInstance().getLogger().info("Database Configuration loaded");
            saveDatabaseConfig(getDatabaseConfig());
            Main.getInstance().getLogger().info("Database Configuration saved");
        } catch (Exception e) {
            Main.getInstance().getLogger().error("An error accorded while loading Database Configuration", e);
        }
    }

    /**
     * Get the Database Configuration
     *
     * @return Database Configuration
     */
    public DatabaseConfig getDatabaseConfig() {
        if (databaseConfig == null) {
            Main.getInstance().getLogger().error("Trying to get Database Configuration before it was initialized!");
            return null;
        }
        return databaseConfig;
    }

    /**
     * Set the Database Configuration
     *
     * @param databaseConfig Database Configuration
     */
    private void setDatabaseConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }
}