package org.kuali.coeus.dc.common.rice.parameter;


import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.setString;

public class ParameterDaoImpl implements ParameterDao {

    private ConnectionDaoService connectionDaoService;

    @Override
    public Parameter getParameter(ParameterKey key) {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt =
                     setString(4, key.getApplicationId(),
                     setString(3, key.getName(),
                     setString(2, key.getComponentCode(),
                     setString(1, key.getNamespaceCode(),
                             connection.prepareStatement("SELECT VAL, PARM_DESC_TXT, PARM_TYP_CD, EVAL_OPRTR_CD " +
                                     " FROM KRCR_PARM_T WHERE NMSPC_CD = ? AND CMPNT_CD = ? AND PARM_NM = ? AND APPL_ID = ?")))));
             ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                Parameter p = new Parameter();
                p.setParameterKey(key);
                p.setValue(result.getString(1));
                p.setDescription(result.getString(2));
                p.setParameterTypeCode(result.getString(3));
                p.setEvaluationOperatorCode(result.getString(4));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteParameter(ParameterKey key) {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt =
                     setString(4, key.getApplicationId(),
                     setString(3, key.getName(),
                     setString(2, key.getComponentCode(),
                     setString(1, key.getNamespaceCode(),
                       connection.prepareStatement("DELETE FROM KRCR_PARM_T WHERE NMSPC_CD = ? AND CMPNT_CD = ? AND PARM_NM = ? AND APPL_ID = ?")))));) {
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
