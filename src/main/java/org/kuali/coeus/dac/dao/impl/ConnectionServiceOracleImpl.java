package org.kuali.coeus.dac.dao.impl;

public class ConnectionServiceOracleImpl extends AbstractConnectionService {

    public static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";

    @Override
    public String getDriverClassName() {
        return JDBC_DRIVER;
    }
}
