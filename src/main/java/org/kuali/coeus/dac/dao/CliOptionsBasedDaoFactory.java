package org.kuali.coeus.dac.dao;

import org.kuali.coeus.dac.CliOptions;
import org.kuali.coeus.dac.dao.impl.DbValidatorServiceMySqlImpl;
import org.kuali.coeus.dac.dao.impl.DbValidatorServiceOracleImpl;

public final class CliOptionsBasedDaoFactory {

    private CliOptions cliOptions;

    public DbValidatorService getDbValidator(){
        if (cliOptions.isMySql()) {
            DbValidatorServiceMySqlImpl validator = new DbValidatorServiceMySqlImpl();
            validator.setCoeusConnection(cliOptions.getCoeusConnectionString());
            validator.setRiceConnection(cliOptions.getRiceConnectionString());
            return validator;
        } else if(cliOptions.isOracle()) {
            DbValidatorServiceOracleImpl validator = new DbValidatorServiceOracleImpl();
            validator.setCoeusConnection(cliOptions.getCoeusConnectionString());
            validator.setRiceConnection(cliOptions.getRiceConnectionString());
            return validator;
        }
        return null;
    }

    public CliOptions getCliOptions() {
        return cliOptions;
    }

    public void setCliOptions(CliOptions cliOptions) {
        this.cliOptions = cliOptions;
    }
}
