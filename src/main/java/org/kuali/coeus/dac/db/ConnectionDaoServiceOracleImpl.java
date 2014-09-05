package org.kuali.coeus.dac.db;

public class ConnectionDaoServiceOracleImpl extends AbstractConnectionDaoService {

    public static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";

    @Override
    public String getDriverClassName() {
        return JDBC_DRIVER;
    }
}
