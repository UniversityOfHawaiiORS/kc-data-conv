package org.kuali.coeus.dac.proposal;

import org.kuali.coeus.dac.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.kuali.coeus.dac.util.PreparedStatementUtils.*;

public class KimAttributeDefnDaoImpl implements ProposalKimAttributeDefnDao {

    private ConnectionDaoService connectionDaoService;

    @Override
    public String getDocumentQualifierAttrDefnId() {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(2, "KC-SYS",
                                     setString(1, "proposal", connection.prepareStatement("SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = ? AND NMSPC_CD = ?")));
            ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                return result.getString(1);
            } else {
                throw new IllegalStateException("proposal attribute definition not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
