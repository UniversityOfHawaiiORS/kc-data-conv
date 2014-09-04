package org.kuali.coeus.dac;


import org.kuali.coeus.dac.dao.CliOptionsBasedDaoFactory;
import org.kuali.coeus.dac.dao.DbValidatorService;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * The main driver program that runs this conversion program.
 */
public final class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        initLogging("/org/kuali/coeus/dac/jul-default.properties");

        CliOptions options = new CliOptions(args);

        if (!options.isValid()) {
            System.out.println(options.getCliHelpString());
            return;
        }

        if (options.containsDebug()) {
            initLogging("/org/kuali/coeus/dac/jul-debug.properties");
        }

        if (options.containsHelp()) {
            System.out.println(options.getCliHelpString());
            return;
        }

        if (options.containsVersion()) {
            System.out.println("6.0-SNAPSHOT");
            return;
        }

        if (options.containsValidate()) {
            CliOptionsBasedDaoFactory factory = new CliOptionsBasedDaoFactory();
            factory.setCliOptions(options);
            DbValidatorService validator = factory.getDbValidator();

            if (validator.isValidCoeusConnection()) {
                System.out.println("COEUS SUCCESS: " + options.getCoeusConnectionString());
            } else {
                System.out.println("COEUS FAILED: " + options.getCoeusConnectionString());
            }

            if (validator.isValidRiceConnection()) {
                System.out.println("RICE SUCCESS: " + options.getRiceConnectionString());
            } else {
                System.out.println("RICE FAILED: " + options.getRiceConnectionString());
            }
        }
    }

    private static void initLogging(String file) {
        String fname = System.getProperty("java.util.logging.config.file");
        if (fname == null) {
            fname = Main.class.getResource(file).getFile();
        }

        try (InputStream in = new FileInputStream(fname); BufferedInputStream bin = new BufferedInputStream(in)) {
            LogManager.getLogManager().readConfiguration(bin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
