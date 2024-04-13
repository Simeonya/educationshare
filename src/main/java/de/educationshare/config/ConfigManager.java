package de.educationshare.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.educationshare.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigManager {
    /**
     * Database Configuration File
     */
    private final File dbConfigFile;

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
        File dataDir = dataDirectory.toFile();
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        dbConfigFile = new File(dataDir, "database.json");
    }

    /**
     * Load the Database Configuration
     *
     * @return Database Configuration
     * @throws IOException If an error occurred while reading the file
     */
    public DatabaseConfig loadDBConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (!dbConfigFile.exists()) {
            return new DatabaseConfig();
        }
        return mapper.readValue(dbConfigFile, DatabaseConfig.class);
    }

    /**
     * Save the Database Configuration
     *
     * @param config Database Configuration
     * @throws IOException If an error occurred while writing the file
     */
    public void saveDBConfig(DatabaseConfig config) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ObjectNode rootNode = mapper.valueToTree(config);
        mapper.writeValue(dbConfigFile, rootNode);
    }

    /**
     * Initialize the ConfigManager
     * Load the Configuration Files
     */
    public void init() {
        try {
            setDatabaseConfig(loadDBConfig());
            saveDBConfig(getDatabaseConfig());
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
        if (databaseConfig != null) {
            Main.getInstance().getLogger().error("Trying to set Database Configuration but its not null!");
            return;
        }
        this.databaseConfig = null;
    }
}