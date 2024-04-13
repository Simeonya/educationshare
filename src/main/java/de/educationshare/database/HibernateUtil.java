package de.educationshare.database;

import de.educationshare.database.objects.AccountObject;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;

import java.util.Properties;

public class HibernateUtil {
    private SessionFactory sessionFactory;

    String host;
    int port;
    String database;
    String username;
    String password;

    public HibernateUtil(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();

            // Database connection properties
            properties.setProperty(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(AvailableSettings.URL, "jdbc:mysql://" + host + ":" + port + "/" + database);
            properties.setProperty(AvailableSettings.USER, username);
            properties.setProperty(AvailableSettings.PASS, password);

            // Auto table creation or update property
            properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");

            // Create a StandardServiceRegistryBuilder
            StandardServiceRegistryBuilder standardRegistryBuilder = new StandardServiceRegistryBuilder();

            // Apply properties to the builder
            standardRegistryBuilder.applySettings(properties);

            // Build StandardServiceRegistry
            StandardServiceRegistry standardRegistry = standardRegistryBuilder.build();

            // Create MetadataSources
            MetadataSources metadataSources = new MetadataSources(standardRegistry);

            // Add your entity classes
            metadataSources.addAnnotatedClass(AccountObject.class);

            // Build Metadata
            Metadata metadata = metadataSources.getMetadataBuilder().build();

            // Create SessionFactoryBuilder
            SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();

            // Build SessionFactory
            sessionFactory = sessionFactoryBuilder.build();
            return sessionFactory;
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + e.getMessage());
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}