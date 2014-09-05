package org.kuali.coeus.dac.db;

import java.sql.*;

public abstract class AbstractConnectionDaoService implements ConnectionDaoService {

    private String riceConnectionString;
    private String coeusConnectionString;

    private static final ThreadLocal<Connection> COEUS_CONNECTIONS = new ThreadLocal<>();
    private static final ThreadLocal<Connection> RICE_CONNECTIONS = new ThreadLocal<>();


    {
        try {
            Class.forName(getDriverClassName()).newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String getDriverClassName();

    @Override
    public Connection getCoeusConnection() {
        Connection c = COEUS_CONNECTIONS.get();
        if (c == null) {
            try {
                c =  DriverManager.getConnection(getCoeusConnectionString());
                c.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            COEUS_CONNECTIONS.set(c);
        }

        return c;
    }

    @Override
    public Connection getRiceConnection() {
        Connection c = RICE_CONNECTIONS.get();
        if (c == null) {
            try {
                c =  DriverManager.getConnection(getRiceConnectionString());
                c.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            RICE_CONNECTIONS.set(c);
        }

        return c;
    }

    public String getRiceConnectionString() {
        return riceConnectionString;
    }

    public void setRiceConnectionString(String riceConnectionString) {
        this.riceConnectionString = riceConnectionString;
    }

    public String getCoeusConnectionString() {
        return coeusConnectionString;
    }

    public void setCoeusConnectionString(String coeusConnectionString) {
        this.coeusConnectionString = coeusConnectionString;
    }
}
