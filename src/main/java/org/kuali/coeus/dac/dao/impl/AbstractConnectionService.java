package org.kuali.coeus.dac.dao.impl;

import org.kuali.coeus.dac.dao.ConnectionService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractConnectionService implements ConnectionService {

    {
        try {
            Class.forName(getDriverClassName()).newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String getDriverClassName();

    @Override
    public Connection getConnection(String connectionString) throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}
