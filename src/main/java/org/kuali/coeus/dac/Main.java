package org.kuali.coeus.dac;


import org.kuali.coeus.dac.db.DbValidatorDaoService;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * The main driver program that runs this conversion program.
 *
 * Example arguments:
 *
 * -debug -dbcoeuscon jdbc:mysql://localhost/kcbnd?user=kcbnd&password=bndpass -dbricecon jdbc:mysql://localhost/kcbnd?user=kcbnd&password=bndpass proposal
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

        CliOptionsBasedDaoFactory factory = new CliOptionsBasedDaoFactory();
        factory.setCliOptions(options);

        if (options.containsValidate()) {
            DbValidatorDaoService validator = factory.getDbValidatorDaoService();

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
            return;
        }


        try (Connection coeusConnection = factory.getConnectionDaoService().getCoeusConnection();
            Connection riceConnection = factory.getConnectionDaoService().getRiceConnection()) {

            if (options.containsProposal()) {
                Collection<String> roleIds = factory.getProposalRoleDao().getRoleIdsToConvert();
                factory.getRoleDao().copyRoleMembersToDocAccessType(roleIds, factory.getProposalKimAttributeDocumentValueHandler());
            }

            if (options.containsIrb()) {
                System.out.println("IRB Conversion not supported");
            }

            if (options.containsIacuc()) {
                System.out.println("IACUC Conversion not supported");
            }

            if (options.containsDryRun()) {
                coeusConnection.rollback();
                riceConnection.rollback();
            } else {
                coeusConnection.commit();
                riceConnection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
