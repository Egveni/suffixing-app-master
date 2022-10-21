package com.epam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveMode {
    private static final Logger logger = Logger.getLogger(MoveMode.class.getName());

    public void fileRenamingInMoveMode() throws IOException {
        FileInputStream propertiesInput;
        Properties property = new Properties();
        String configFilePath = "src/main/resources/config.properties";
        propertiesInput = new FileInputStream(configFilePath);
        property.load(propertiesInput);
        String filesSource = property.getProperty("files");
        if (filesSource.equalsIgnoreCase("")) {
            logger.log(Level.WARNING, "No files are configured to be copied/moved");
            return;
        }
        List<String> files = Arrays.asList(filesSource.split(":"));
        for (String file : files) {
            File originalFile = new File(file);
            if (!originalFile.exists()) {
                logger.log(Level.INFO, "No such file: " + originalFile.getName());
                continue;
            }
            String suffix = property.getProperty("suffix");
            if (suffix.equalsIgnoreCase("")) {
                logger.log(Level.SEVERE, "No suffix is configured");
                break;
            } else {
                int index = file.lastIndexOf("files/");
                String filePath1 = file.substring(0, index + 6);
                String filePath2 = file.substring(index + 6);
                File renamedFiles = new File(filePath1 + suffix + filePath2);
                logger.log(Level.INFO, originalFile.getAbsolutePath() + "=>" + renamedFiles.getAbsolutePath());
            }
        }
    }
}
