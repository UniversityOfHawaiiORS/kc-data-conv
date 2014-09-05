package org.kuali.coeus.dac.db;

public class ConnectionDaoServiceMySqlImpl extends AbstractConnectionDaoService {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    @Override
    public String getDriverClassName() {
        return JDBC_DRIVER;
    }
}
