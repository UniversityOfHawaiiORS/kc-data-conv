package org.kuali.coeus.dac.kim;

import org.kuali.coeus.dac.db.ConnectionDaoService;
import org.kuali.coeus.dac.kim.KimTypeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KimTypeDaoImpl implements KimTypeDao {

    private ConnectionDaoService connectionService;

    @Override
    public String getDocAccessKimTypeId() {
        Connection connection = connectionService.getRiceConnection();
        try (PreparedStatement stmt = connection.prepareStatement("select KIM_TYP_ID from KRIM_TYP_T WHERE NM = 'Derived Role: Document Access' AND NMSPC_CD = 'KC-SYS'");
             ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                return result.getString(1);
            } else {
                throw new IllegalStateException("can't find document access kim type");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionDaoService getConnectionService() {
        return connectionService;
    }

    public void setConnectionService(ConnectionDaoService connectionService) {
        this.connectionService = connectionService;
    }
}
