package org.kuali.coeus.dc;

import org.kuali.coeus.dc.access.kim.*;
import org.kuali.coeus.dc.access.proposal.*;
import org.kuali.coeus.dc.common.db.*;

public final class CliOptionsBasedDaoFactory {

    private CliOptions cliOptions;

    public DbValidatorDaoService getDbValidatorDaoService(){
        if (cliOptions.isMySql()) {
            DbValidatorDaoServiceMySqlImpl service = new DbValidatorDaoServiceMySqlImpl();
            service.setConnectionService(getConnectionDaoService());
            return service;
        } else if(cliOptions.isOracle()) {
            DbValidatorDaoServiceOracleImpl service = new DbValidatorDaoServiceOracleImpl();
            service.setConnectionService(getConnectionDaoService());
            return service;
        }
        return null;
    }

    public ConnectionDaoService getConnectionDaoService() {
        if (cliOptions.isMySql()) {
            ConnectionDaoServiceMySqlImpl connectionService = new ConnectionDaoServiceMySqlImpl();
            connectionService.setCoeusConnectionString(cliOptions.getCoeusConnectionString());
            connectionService.setRiceConnectionString(cliOptions.getRiceConnectionString());
            return connectionService;
        } else if(cliOptions.isOracle()) {
            ConnectionDaoServiceOracleImpl connectionService = new ConnectionDaoServiceOracleImpl();
            connectionService.setCoeusConnectionString(cliOptions.getCoeusConnectionString());
            connectionService.setRiceConnectionString(cliOptions.getRiceConnectionString());
            return connectionService;
        }
        return null;
    }

    public RoleDao getRoleDao() {
        RoleDaoImpl dao = new RoleDaoImpl();
        dao.setConnectionDaoService(getConnectionDaoService());
        dao.setKimTypeDao(getKimTypeDao());
        dao.setSequenceDaoService(getSequenceDaoService());

        return dao;
    }

    public KimTypeDao getKimTypeDao() {
        KimTypeDaoImpl dao = new KimTypeDaoImpl();
        dao.setConnectionService(getConnectionDaoService());
        return dao;
    }

    public SequenceDaoService getSequenceDaoService() {
        if (cliOptions.isMySql()) {
            SequenceDaoServiceMySqlImpl sequenceDaoService = new SequenceDaoServiceMySqlImpl();
            sequenceDaoService.setConnectionDaoService(getConnectionDaoService());
            return sequenceDaoService;
        } else if(cliOptions.isOracle()) {
            SequenceDaoServiceOracleImpl sequenceDaoService = new SequenceDaoServiceOracleImpl();
            sequenceDaoService.setConnectionDaoService(getConnectionDaoService());
            return sequenceDaoService;
        }
        return null;
    }

    public ProposalKimAttributeDefnDao getProposalKimAttributeDefnDao() {
        KimAttributeDefnDaoImpl dao = new KimAttributeDefnDaoImpl();
        dao.setConnectionDaoService(getConnectionDaoService());
        return dao;
    }

    public KimAttributeDocumentValueHandler getProposalKimAttributeDocumentValueHandler() {
        ProposalKimAttributeDocumentValueHandler handler = new ProposalKimAttributeDocumentValueHandler();
        handler.setProposalKimAttributeDefnDao(getProposalKimAttributeDefnDao());
        handler.setConnectionDaoService(getConnectionDaoService());

        return handler;
    }

    public ProposalRoleDao getProposalRoleDao() {
        ProposalRoleDaoImpl rs = new ProposalRoleDaoImpl();
        rs.setConnectionDaoService(getConnectionDaoService());
        return rs;
    }

    public CliOptions getCliOptions() {
        return cliOptions;
    }

    public void setCliOptions(CliOptions cliOptions) {
        this.cliOptions = cliOptions;
    }
}
