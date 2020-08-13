package com.dexter.kabutar;

import org.testcontainers.containers.PostgreSQLContainer;

public final class PostgressTestContainer extends PostgreSQLContainer<PostgressTestContainer> {

    private static final String IMAGE_VERSION = "postgres:11.1";

    private static PostgressTestContainer postgressTestContainer;

    private PostgressTestContainer(){
        super(IMAGE_VERSION);
    }

    public static PostgressTestContainer getInstance(){
        if(postgressTestContainer == null){
            postgressTestContainer = new PostgressTestContainer();
        }
        return postgressTestContainer;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", postgressTestContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgressTestContainer.getUsername());
        System.setProperty("DB_PASSWORD", postgressTestContainer.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
