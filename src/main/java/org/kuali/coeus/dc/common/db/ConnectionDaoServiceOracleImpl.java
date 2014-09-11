package org.kuali.coeus.dc.common.db;

public class ConnectionDaoServiceOracleImpl extends AbstractConnectionDaoService {

    public static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";

    @Override
    public String getDriverClassName() {
        return JDBC_DRIVER;
    }
}
