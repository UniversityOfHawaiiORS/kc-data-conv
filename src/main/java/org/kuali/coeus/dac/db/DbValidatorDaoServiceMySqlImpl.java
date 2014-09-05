package org.kuali.coeus.dac.db;

public class DbValidatorDaoServiceMySqlImpl extends AbstractDbValidatorDaoService {

    public static final String VALIDATION_QUERY = "select 1";

    @Override
    public String getValidationQuery() {
        return VALIDATION_QUERY;
    }
}
