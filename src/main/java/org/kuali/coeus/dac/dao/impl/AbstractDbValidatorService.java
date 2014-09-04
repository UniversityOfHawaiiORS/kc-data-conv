package org.kuali.coeus.dac.dao.impl;

import org.kuali.coeus.dac.dao.ConnectionService;
import org.kuali.coeus.dac.dao.DbValidatorService;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDbValidatorService implements DbValidatorService {

    private static final Logger LOG = Logger.getLogger(AbstractDbValidatorService.class.getName());

    private String riceConnection;
    private String coeusConnection;

    private ConnectionService connectionService;

    @Override
    public boolean isValidRiceConnection() {
        return isValidConnection(riceConnection);
    }

    @Override
    public boolean isValidCoeusConnection() {
        return isValidConnection(coeusConnection);
    }

    protected boolean isValidConnection(String connection) {

        try (Connection conn = connectionService.getConnection(connection);
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

    public ConnectionService getConnectionService() {
        return connectionService;
    }

    public void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public abstract String getValidationQuery();
}
