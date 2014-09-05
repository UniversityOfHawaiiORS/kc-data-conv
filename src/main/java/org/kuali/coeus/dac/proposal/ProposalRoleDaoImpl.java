package org.kuali.coeus.dac.proposal;

import org.kuali.coeus.dac.db.ConnectionDaoService;
import org.kuali.coeus.dac.proposal.ProposalRoleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ProposalRoleDaoImpl implements ProposalRoleDao {

    private ConnectionDaoService connectionDaoService;

    @Override
    public Collection<String> getRoleIdsToConvert() {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = connection.prepareStatement("select ROLE_ID from krim_role_t where NMSPC_CD = 'KC-PD'");
            ResultSet result = stmt.executeQuery()) {

            final Collection<String> roleIds = new ArrayList<>();
            while (result.next()) {
                roleIds.add(result.getString(1));
            }
            return roleIds;
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
