package org.kuali.coeus.dc.access.proposal;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.*;

public class KimAttributeDefnDaoImpl implements ProposalKimAttributeDefnDao {

    public static final String KC_SYS = "KC-SYS";
    public static final String PROPOSAL = "proposal";

    private ConnectionDaoService connectionDaoService;

    @Override
    public String getDocumentQualifierAttrDefnId() {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(2, KC_SYS,
                                     setString(1, PROPOSAL, connection.prepareStatement("SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = ? AND NMSPC_CD = ?")));
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

    @Override
    public void deleteDocumentQualifierAttrDefn() {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(2, KC_SYS,
                setString(1, PROPOSAL, connection.prepareStatement("DELETE FROM KRIM_ATTR_DEFN_T WHERE NM = ? AND NMSPC_CD = ?")))) {
            stmt.executeUpdate();
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
