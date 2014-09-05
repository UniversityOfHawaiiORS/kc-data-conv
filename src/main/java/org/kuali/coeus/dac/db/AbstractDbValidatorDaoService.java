package org.kuali.coeus.dac.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDbValidatorDaoService implements DbValidatorDaoService {

    private static final Logger LOG = Logger.getLogger(AbstractDbValidatorDaoService.class.getName());

    private ConnectionDaoService connectionService;

    @Override
    public boolean isValidRiceConnection() {
        Connection conn = connectionService.getRiceConnection();
        try (PreparedStatement stmt = conn.prepareStatement(getValidationQuery());
             ResultSet rs = stmt.executeQuery()) {
            return true;
        } catch (SQLException e) {
            LOG.log(Level.INFO, "validation failed", e);
        }
        return false;
    }

    @Override
    public boolean isValidCoeusConnection() {
        final Connection conn = connectionService.getCoeusConnection();
        try (PreparedStatement stmt = conn.prepareStatement(getValidationQuery());
             ResultSet rs = stmt.executeQuery()) {
            return true;
        } catch (SQLException e) {
            LOG.log(Level.INFO, "validation failed", e);
        }
        return false;
    }

    public ConnectionDaoService getConnectionService() {
        return connectionService;
    }

    public void setConnectionService(ConnectionDaoService connectionService) {
        this.connectionService = connectionService;
    }

    public abstract String getValidationQuery();
}
