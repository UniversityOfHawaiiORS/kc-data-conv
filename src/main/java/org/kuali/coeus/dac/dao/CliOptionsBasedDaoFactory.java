package org.kuali.coeus.dac.dao;

import org.kuali.coeus.dac.CliOptions;
import org.kuali.coeus.dac.dao.impl.ConnectionServiceMysqlImpl;
import org.kuali.coeus.dac.dao.impl.DbValidatorServiceMySqlImpl;
import org.kuali.coeus.dac.dao.impl.DbValidatorServiceOracleImpl;

public final class CliOptionsBasedDaoFactory {

    private CliOptions cliOptions;

    public DbValidatorService getDbValidatorService(){
        if (cliOptions.isMySql()) {
            DbValidatorServiceMySqlImpl service = new DbValidatorServiceMySqlImpl();
            service.setCoeusConnection(cliOptions.getCoeusConnectionString());
            service.setRiceConnection(cliOptions.getRiceConnectionString());
            service.setConnectionService(getConnectionService());
            return service;
        } else if(cliOptions.isOracle()) {
            DbValidatorServiceOracleImpl service = new DbValidatorServiceOracleImpl();
            service.setCoeusConnection(cliOptions.getCoeusConnectionString());
            service.setRiceConnection(cliOptions.getRiceConnectionString());
            service.setConnectionService(getConnectionService());
            return service;
        }
        return null;
    }

    public ConnectionService getConnectionService() {
        if (cliOptions.isMySql()) {
            return new ConnectionServiceMysqlImpl();
        } else if(cliOptions.isOracle()) {
            return new ConnectionServiceMysqlImpl();
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
