package org.kuali.coeus.dac.dao.impl;

public class ConnectionServiceMysqlImpl extends AbstractConnectionService {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    @Override
    public String getDriverClassName() {
        return JDBC_DRIVER;
    }
}
