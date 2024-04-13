package de.educationshare.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatabaseConfig {
    /**
     * Database Host
     */
    @JsonProperty("Database.Host")
    private String host;

    /**
     * Database Port
     */
    @JsonProperty("Database.Port")
    private int port;

    /**
     * Database User
     */
    @JsonProperty("Database.User")
    private String user;

    /**
     * Database Password
     */
    @JsonProperty("Database.Password")
    private String password;

    /**
     * Database Name
     */
    @JsonProperty("Database.Name")
    private String name;

    /**
     * Get the Database Host
     *
     * @return MariaDB/MySQL Database Host
     */
    public String getHost() {
        return host;
    }

    /**
     * Set the Database Host
     *
     * @param host MariaDB/MySQL Database Host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Get the Database Port
     *
     * @return MariaDB/MySQL Database Port
     */
    public int getPort() {
        return port;
    }

    /**
     * Set the Database Port
     *
     * @param port MariaDB/MySQL Database Port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Get the Database User
     *
     * @return MariaDB/MySQL Database User
     */
    public String getUser() {
        return user;
    }

    /**
     * Set the Database User
     *
     * @param user MariaDB/MySQL Database User
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Get the Database Password
     *
     * @return MariaDB/MySQL Database Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the Database Password
     *
     * @param password MariaDB/MySQL Database Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the Database Name
     *
     * @return MariaDB/MySQL Database Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Database Name
     *
     * @param name MariaDB/MySQL Database Name
     */
    public void setName(String name) {
        this.name = name;
    }
}
