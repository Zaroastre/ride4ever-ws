/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.registry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * ApplicationResource
 */
public class ApplicationConfiguration {
    private static final ApplicationConfiguration singleton = new ApplicationConfiguration();
    
    public static final ApplicationConfiguration getInstance() {
        return singleton;
    }

    private final File configuration;

    private final File load(final String resourcePath) {
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
            if (inputStream == null) {
                return null;
            }

            File tempFile = File.createTempFile(String.valueOf(inputStream.hashCode()), ".tmp");
            tempFile.deleteOnExit();

            try (FileOutputStream destination = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    destination.write(buffer, 0, bytesRead);
                }
            }
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ApplicationConfiguration() {
        this.configuration = this.load("application.properties");
    }

    public final String getProperty(final String key) {
        if (this.configuration != null) {
            String value = null;
            if (this.configuration.exists()) {
                try {
                    Scanner scanner = new Scanner(this.configuration);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.contains("=")) {
                            String[] data = line.split("=", 2);
                            if (data.length == 2) {
                                if (data[0].equalsIgnoreCase(key)) {
                                    value = data[1];
                                }
                            }
                        }
                    }
                    scanner.close();
                  } catch (FileNotFoundException exception) {
                    System.err.println("An error occurred: ");
                    System.err.println(exception.getMessage());
                    exception.printStackTrace();
                  }
            }
            return value;
        }
        return null;
    }
}