package org.kuali.coeus.dac.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceDaoServiceOracleImpl implements SequenceDaoService {

    private ConnectionDaoService connectionDaoService;

    @Override
    public String getNextRiceSequence(String sequenceName, String prefix) {
        Connection connection = connectionDaoService.getRiceConnection();
        try {
            return getNextSequence(sequenceName, prefix, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextCoeusSequence(String sequenceName, String prefix) {
        Connection connection = connectionDaoService.getCoeusConnection();
        try {
            return getNextSequence(sequenceName, prefix, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getNextSequence(String sequenceName, String prefix, Connection connection) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT " + sequenceName + ".NEXTVAL FROM DUAL");
             ResultSet result = stmt.executeQuery()) {
            return prefix + result.getString(1);
        }
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
