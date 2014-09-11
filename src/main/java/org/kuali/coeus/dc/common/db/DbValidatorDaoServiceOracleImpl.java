package org.kuali.coeus.dc.common.db;

public class DbValidatorDaoServiceOracleImpl extends AbstractDbValidatorDaoService {

    public static final String VALIDATION_QUERY = "select 1 from dual";

    @Override
    public String getValidationQuery() {
        return VALIDATION_QUERY;
    }
}
