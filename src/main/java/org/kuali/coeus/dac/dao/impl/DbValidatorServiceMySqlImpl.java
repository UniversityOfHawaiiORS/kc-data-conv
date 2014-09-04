package org.kuali.coeus.dac.dao.impl;

public class DbValidatorServiceMySqlImpl extends AbstractDbValidatorService {

    public static final String VALIDATION_QUERY = "select 1";

    @Override
    public String getValidationQuery() {
        return VALIDATION_QUERY;
    }
}
