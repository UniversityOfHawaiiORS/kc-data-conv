package org.kuali.coeus.dc;

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

        if (valid && (containsProposal() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        if (valid && (containsIrb() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        if (valid && (containsIacuc() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
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

    public boolean containsDryRun() {
        return contains("-dryrun");
    }

    public boolean containsValidate() {
        return contains("-validate");
    }

    public boolean containsProposal() {
        return contains("proposal");
    }

    public boolean containsIrb() {
        return contains("irb");
    }

    public boolean containsIacuc() {
        return contains("iacuc");
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
                + "The valid conversion targets are (proposal|irb|iacuc)\n"
                + "\n"
                + "The dryrun flag may still cause database sequences to increment";
    }
}
