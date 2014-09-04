package org.kuali.coeus.dac.dao.impl;

public class DbValidatorServiceMySqlImpl extends AbstractDbValidator {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String VALIDATION_QUERY = "select 1";

    @Override
    public String getValidationQuery() {
        return VALIDATION_QUERY;
    }

    @Override
    public String getDriverClassName() {
        return JDBC_DRIVER;
    }
}
