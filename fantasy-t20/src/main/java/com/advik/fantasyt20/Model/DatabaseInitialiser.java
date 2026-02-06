package com.advik.fantasyt20.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import jakarta.annotation.PostConstruct;

public class DatabaseInitialiser {
    @PostConstruct
    public void init() throws IOException {
        java.io.File volumeDb = new java.io.File("/data/registration.db");
        if (!volumeDb.exists()) {
            // copy from project root to volume
            Path source = Path.of("registration.db"); // your repo DB
            Files.copy(source, volumeDb.toPath());
            System.out.println("Copied DB to volume!");
        }
    }

}
