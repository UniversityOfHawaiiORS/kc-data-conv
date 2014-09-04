package org.kuali.coeus.dac.dao.impl;

public class DbValidatorServiceOracleImpl extends AbstractDbValidatorService {

    public static final String VALIDATION_QUERY = "select 1 from dual";

    @Override
    public String getValidationQuery() {
        return VALIDATION_QUERY;
    }
}
