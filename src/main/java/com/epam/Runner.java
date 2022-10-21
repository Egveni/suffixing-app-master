package com.epam;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Runner {
    private static final Logger logger = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) throws IOException {
        FileInputStream propertiesInput;
        Properties property = new Properties();
        String configFilePath = "src/main/resources/config.properties";
        propertiesInput = new FileInputStream(configFilePath);
        property.load(propertiesInput);
        String mode = property.getProperty("mode");
        if (mode.equalsIgnoreCase("copy")) {
            CopyMode copyMode = new CopyMode();
            copyMode.fileRenamingInCopyMode();
        } else if (mode.equalsIgnoreCase("move")) {
            MoveMode moveMode = new MoveMode();
            moveMode.fileRenamingInMoveMode();
        } else {
            logger.log(Level.SEVERE, "Mode is not recognized: " + mode);
        }
    }
}
