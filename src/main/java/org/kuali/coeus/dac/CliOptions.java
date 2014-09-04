package org.kuali.coeus.dac;

public class CliOptions {

    private String[] args;

    public CliOptions(String[] args) {
        this.args = args;
    }

    public boolean isValid() {
        boolean valid = args != null && args.length > 0;
        if (valid && (containsHelp() || containsVersion())) {
            return true;
        }

        if (valid && (containsValidate() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        return false;
    }

    public boolean containsHelp() {
        return contains("-help");
    }

    public boolean containsVersion() {
        return contains("-version");
    }

    public boolean containsDebug() {
        return contains("-debug");
    }

    public boolean containsValidate() {
        return contains("-validate");
    }

    private boolean contains(String name) {
        for (String arg : args) {
            if (name.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    private String nextArg(String name) {
        boolean returnNext = false;
        for (String arg : args) {
            if (returnNext) {
                return arg;
            }

            if (name.equals(arg)) {
                returnNext = true;
            }
        }
        return "";
    }

    public String getCoeusConnectionString() {
        if (contains("-dbcoeuscon")) {
            return nextArg("-dbcoeuscon");
        }
        return "";
    }

    public String getRiceConnectionString() {
        if (contains("-dbricecon")) {
            return nextArg("-dbricecon");
        }
        return "";
    }

    public boolean isMySql() {
        if (contains("-platform") && "MySql".equalsIgnoreCase(nextArg("-platform"))) {
            return true;
        } else if (contains("-dbricecon") && nextArg("-dbricecon").startsWith("jdbc:mysql")) {
            return true;
        } else if (contains("-dbcoeuscon") && nextArg("-dbcoeuscon").startsWith("jdbc:mysql")) {
            return true;
        }

        return false;
    }

    public boolean isOracle() {
        if (contains("-platform") && "Oracle".equalsIgnoreCase(nextArg("-platform"))) {
            return true;
        } else if (contains("-dbricecon") && nextArg("-dbricecon").startsWith("jdbc:oracle")) {
            return true;
        } else if (contains("-dbcoeuscon") && nextArg("-dbcoeuscon").startsWith("jdbc:oracle")) {
            return true;
        }

        return false;
    }

    public String getCliHelpString() {
        return "coeus-doc-access-conv [options] [conv_target [conv_target2 [conv_target3] ...]]\n"
                + "  Options:\n"
                + "  -help                    print this message\n"
                + "  -version                 print the version information and exit\n"
                + "  -dryrun                  executes conversion without writing out to the database\n"
                + "  -validate                validates database connections only\n"
                + "  -debug                   print debugging information\n"
                + "  -dbplatform <platform>   the database platform (MySql|Oracle)\n"
                + "  -dbricecon <connection>  the kuali rice jdbc database connection string (jdbc:mysql://localhost/rice?user=usr&password=pwd)\n"
                + "  -dbcoeuscon <connection> the kuali coeus jdbc database connection string (jdbc:mysql://localhost/coeus?user=usr&password=pwd)\n"
                + "\n"
                + "If platform is not specified then the platform will be autodetected from the connection strings.\n"
                + "\n"
                + "The valid conversion targets are (proposal|irb|iacuc)";
    }

    /*
    private static final Options cliOptions;

    public static final String OPTION_HELP = "help";
    public static final String OPTION_VERSION = "version";
    public static final String OPTION_DRYRUN = "dryrun";
    public static final String OPTION_DEBUG = "debug";
    public static final String OPTION_DBPLATFORM = "dbplatform";
    public static final String OPTION_DB_RICE_CON = "dbricecon";
    public static final String OPTION_DB_COEUS_CON = "dbcoeuscon";

    static {
        Option help = new Option(OPTION_HELP, "print this message");
        Option version = new Option(OPTION_VERSION, "print the version information and exit");
        Option dryRun = new Option(OPTION_DRYRUN, "Executes conversion without writing out to the database");
        Option debug = new Option(OPTION_DEBUG, "print debugging information");

        Option dbplatform = new Option(OPTION_DBPLATFORM, "The database platform (MySql|Oracle)");
        Option dbricecon = OptionBuilder.hasArgs(1)
                .isRequired(true)
                .withDescription("The kuali rice jdbc database connection string (jdbc:mysql://localhost/rice?user=usr&password=pwd)")
                .create(OPTION_DB_RICE_CON);
        Option dbcoeuscon = OptionBuilder.hasArgs(1)
                .isRequired(true)
                .withDescription("The kuali coeus jdbc database connection string (jdbc:mysql://localhost/coeus?user=usr&password=pwd)")
                .create(OPTION_DB_COEUS_CON);

        cliOptions = new Options();
        cliOptions.addOption(help);
        cliOptions.addOption(version);
        cliOptions.addOption(dryRun);
        cliOptions.addOption(debug);
        cliOptions.addOption(dbplatform);
        cliOptions.addOption(dbricecon);
        cliOptions.addOption(dbcoeuscon);
    }

    public static Options getCliOptions() {
        return cliOptions;
    }*/
}
