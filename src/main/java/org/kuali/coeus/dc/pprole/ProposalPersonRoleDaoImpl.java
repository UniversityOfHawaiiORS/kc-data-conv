package org.kuali.coeus.dc.pprole;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;
import org.kuali.coeus.dc.common.rice.parameter.Parameter;
import org.kuali.coeus.dc.common.rice.parameter.ParameterDao;
import org.kuali.coeus.dc.common.rice.parameter.ParameterKey;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.setString;

public class ProposalPersonRoleDaoImpl implements ProposalPersonRoleDao {

    private static final Logger LOG = Logger.getLogger(ProposalPersonRoleDaoImpl.class.getName());

    private static final ParameterKey COI = new ParameterKey("KC-GEN","All","personrole.coi","KC");
    private static final ParameterKey KP = new ParameterKey("KC-GEN","All","personrole.kp","KC");
    private static final ParameterKey NIH_COI = new ParameterKey("KC-GEN","All","personrole.nih.coi","KC");
    private static final ParameterKey NIH_COI_MPI = new ParameterKey("KC-GEN","All","personrole.nih.coi.mpi","KC");
    private static final ParameterKey NIH_KP = new ParameterKey("KC-GEN","All","personrole.nih.kp","KC");
    private static final ParameterKey NIH_PI = new ParameterKey("KC-GEN","All","personrole.nih.pi","KC");
    private static final ParameterKey PI = new ParameterKey("KC-GEN","All","personrole.pi","KC");
    private static final ParameterKey READ_ONLY_ROLES = new ParameterKey("KC-GEN","All","personrole.readonly.roles","KC");
    private static final String DEFAULT = "DEFAULT";
    private static final String NIH_MULTIPLE_PI = "NIH Multiple PI";

    private ParameterDao parameterDao;
    private ConnectionDaoService connectionDaoService;

    @Override
    public void convertParameterValues() {
        Parameter pCoi = getParameter(COI);
        if (pCoi != null) {
            updateProposalPersonRoleDescription("COI", DEFAULT, pCoi.getValue());
            deleteParameter(COI);
        }

        Parameter pKp = getParameter(KP);
        if (pKp != null) {
            updateProposalPersonRoleDescription("KP", DEFAULT, pKp.getValue());
            deleteParameter(KP);
        }

        Parameter pPi = getParameter(PI);
        if (pPi != null) {
            updateProposalPersonRoleDescription("PI", DEFAULT, pPi.getValue());
            deleteParameter(PI);
        }

        Parameter pNihPi = getParameter(NIH_PI);
        if (pNihPi != null) {
            updateProposalPersonRoleDescription("PI", NIH_MULTIPLE_PI, pNihPi.getValue());
            deleteParameter(NIH_PI);
        }

        Parameter pNihCoiMpi = getParameter(NIH_COI_MPI);
        if (pNihCoiMpi != null) {
            updateProposalPersonRoleDescription("MPI", NIH_MULTIPLE_PI, pNihCoiMpi.getValue());
            deleteParameter(NIH_COI_MPI);
        }

        Parameter pNihCoi = getParameter(NIH_COI);
        if (pNihCoi != null) {
            updateProposalPersonRoleDescription("COI", NIH_MULTIPLE_PI, pNihCoi.getValue());
            deleteParameter(NIH_COI);
        }

        Parameter pNihKp = getParameter(NIH_KP);
        if (pNihKp != null) {
            updateProposalPersonRoleDescription("KP", NIH_MULTIPLE_PI, pNihKp.getValue());
            deleteParameter(NIH_KP);
        }

        Parameter pReadOnlyRoles = getParameter(READ_ONLY_ROLES);

        //this parameter only has role codes
        if (pReadOnlyRoles != null) {
            final Set<String> roles = new HashSet<>();
            for (String role : Arrays.asList(pReadOnlyRoles.getValue().split(","))) {
                roles.add(role.trim().toUpperCase());
            }

            if (roles.contains("COI")) {
                updateProposalPersonRoleToReadOnly("COI", DEFAULT, true);
            } else {
                updateProposalPersonRoleToReadOnly("COI", DEFAULT, false);
            }

            if (roles.contains("KP")) {
                updateProposalPersonRoleToReadOnly("KP", DEFAULT, true);
            } else {
                updateProposalPersonRoleToReadOnly("KP", DEFAULT, false);
            }

            if (roles.contains("PI")) {
                updateProposalPersonRoleToReadOnly("PI", DEFAULT, true);
            } else {
                updateProposalPersonRoleToReadOnly("PI", DEFAULT, false);
            }

            if (roles.contains("NIH.PI")) {
                updateProposalPersonRoleToReadOnly("PI", NIH_MULTIPLE_PI, true);
            } else {
                updateProposalPersonRoleToReadOnly("PI", NIH_MULTIPLE_PI, false);
            }

            if (roles.contains("NIH.COI.MPI")) {
                updateProposalPersonRoleToReadOnly("MPI", NIH_MULTIPLE_PI, true);
            } else {
                updateProposalPersonRoleToReadOnly("MPI", NIH_MULTIPLE_PI, false);
            }

            if (roles.contains("NIH.COI")) {
                updateProposalPersonRoleToReadOnly("COI", NIH_MULTIPLE_PI, true);
            } else {
                updateProposalPersonRoleToReadOnly("COI", NIH_MULTIPLE_PI, false);
            }

            if (roles.contains("NIH.KP")) {
                updateProposalPersonRoleToReadOnly("KP", NIH_MULTIPLE_PI, true);
            } else {
                updateProposalPersonRoleToReadOnly("KP", NIH_MULTIPLE_PI, false);
            }
            deleteParameter(READ_ONLY_ROLES);
        }

    }

    private Parameter getParameter(ParameterKey key) {
        Parameter p = parameterDao.getParameter(key);
        if (p != null) {
            LOG.info("Found parameter " + key + " with value " + p.getValue());
        }
        return p;
    }

    private void deleteParameter(ParameterKey key) {
        LOG.info("Delete parameter " + key);
        parameterDao.deleteParameter(key);
    }

    private void updateProposalPersonRoleDescription(String roleCode, String shn, String description) {
        LOG.info("Updating Proposal Person Role with role code " + roleCode + " and sponsor hierarchy name " + shn + ". Setting description to " + description + ".");

        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(3, shn,
                setString(2, roleCode,
                setString(1, description, connection.prepareStatement("UPDATE eps_prop_person_role SET DESCRIPTION = ? WHERE PROP_PERSON_ROLE_CODE = ? AND SPONSOR_HIERARCHY_NAME = ?"))))) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateProposalPersonRoleToReadOnly(String roleCode, String shn, boolean readOnly) {
        String flag = readOnly ? "Y" : "N";

        LOG.info("Updating Proposal Person Role with role code " + roleCode + " and sponsor hierarchy name " + shn + ". Setting read only to " + flag + ".");
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(3, shn,
                setString(2, roleCode,
                        setString(1, flag, connection.prepareStatement("UPDATE eps_prop_person_role SET READ_ONLY_ROLE = ? WHERE PROP_PERSON_ROLE_CODE = ? AND SPONSOR_HIERARCHY_NAME = ?"))))) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ParameterDao getParameterDao() {
        return parameterDao;
    }

    public void setParameterDao(ParameterDao parameterDao) {
        this.parameterDao = parameterDao;
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
