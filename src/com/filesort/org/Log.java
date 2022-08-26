package com.filesort.org;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.File;
import java.io.IOException;

public class Log {

    public Logger logger;
    FileHandler fh;

    public Log(String file_name) throws SecurityException, IOException {

        File f = new File(file_name);
        if (!f.exists()) {
            f.createNewFile();
        }

        fh = new FileHandler(file_name, true);
        logger = Logger.getLogger("test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

    }

    public void logWarning(String text) {
        try {

            logger.setLevel(Level.WARNING);
            logger.warning(text);

        } catch (SecurityException e) {

            e.printStackTrace();
        }
    }

    public void logInfo(String text) {
        try {
            logger.setLevel(Level.INFO);
            logger.info(text);

        } catch (SecurityException e) {

            e.printStackTrace();
        }
    }

    public void logSevere(String text) {
        try {

            logger.setLevel(Level.SEVERE);
            logger.severe(text);

        } catch (SecurityException e) {

            e.printStackTrace();
        }
    }

}
