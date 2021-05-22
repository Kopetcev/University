package by.kopetcev.university.dao;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresDatabaseContainer extends PostgreSQLContainer<PostgresDatabaseContainer> {
    private static final String IMAGE_VERSION = "postgres:13";
    private static PostgresDatabaseContainer container;

    private PostgresDatabaseContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgresDatabaseContainer getInstance() {
        if (container == null) {
            container = new PostgresDatabaseContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}

