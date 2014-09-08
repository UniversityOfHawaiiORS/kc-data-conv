package org.kuali.coeus.dac.proposal;

import org.kuali.coeus.dac.db.ConnectionDaoService;
import org.kuali.coeus.dac.kim.KimAttributeDocumentValueHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.kuali.coeus.dac.util.PreparedStatementUtils.*;

public class ProposalKimAttributeDocumentValueHandler implements KimAttributeDocumentValueHandler {

    private static final Logger LOG = Logger.getLogger(ProposalKimAttributeDocumentValueHandler.class.getName());

    private ProposalKimAttributeDefnDao proposalKimAttributeDefnDao;
    private ConnectionDaoService connectionDaoService;


    @Override
    public String transform(String val) {
        Connection connection = connectionDaoService.getCoeusConnection();
        try (PreparedStatement stmt = setString(1, val, connection.prepareStatement("SELECT DOCUMENT_NUMBER FROM EPS_PROPOSAL WHERE PROPOSAL_NUMBER = ?"));
            ResultSet result = stmt.executeQuery()) {
            if (result.next()) {
                return result.getString(1);
            } else {
                LOG.warning("cannot find document number for proposal: " + val);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean isDocumentValueType(String attrDefnId) {
        return proposalKimAttributeDefnDao.getDocumentQualifierAttrDefnId().equals(attrDefnId);
    }

    @Override
    public void cleanup() {
        proposalKimAttributeDefnDao.deleteDocumentQualifierAttrDefn();
    }

    public ProposalKimAttributeDefnDao getProposalKimAttributeDefnDao() {
        return proposalKimAttributeDefnDao;
    }

    public void setProposalKimAttributeDefnDao(ProposalKimAttributeDefnDao proposalKimAttributeDefnDao) {
        this.proposalKimAttributeDefnDao = proposalKimAttributeDefnDao;
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
