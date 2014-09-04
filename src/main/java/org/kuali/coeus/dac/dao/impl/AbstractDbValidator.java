package org.kuali.coeus.dac.dao.impl;

import org.kuali.coeus.dac.dao.DbValidatorService;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDbValidator implements DbValidatorService {

    private static final Logger LOG = Logger.getLogger(AbstractDbValidator.class.getName());

    private String riceConnection;
    private String coeusConnection;

    @Override
    public boolean isValidRiceConnection() {
        return isValidConnection(riceConnection);
    }

    @Override
    public boolean isValidCoeusConnection() {
        return isValidConnection(coeusConnection);
    }

    protected boolean isValidConnection(String connection) {
        try {
            Class.forName(getDriverClassName()).newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(connection);
             PreparedStatement stmt = conn.prepareStatement(getValidationQuery());
             ResultSet rs = stmt.executeQuery()) {
            return true;
        } catch (SQLException e) {
            LOG.log(Level.INFO, "validation failed", e);
        }
        return false;
    }

    public String getRiceConnection() {
        return riceConnection;
    }

    public void setRiceConnection(String riceConnection) {
        this.riceConnection = riceConnection;
    }

    public String getCoeusConnection() {
        return coeusConnection;
    }

    public void setCoeusConnection(String coeusConnection) {
        this.coeusConnection = coeusConnection;
    }

    public abstract String getValidationQuery();
    public abstract String getDriverClassName();
}
