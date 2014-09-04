package org.kuali.coeus.dac.dao.impl;

public class DbValidatorServiceOracleImpl extends AbstractDbValidator {

    public static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    public static final String VALIDATION_QUERY = "select 1 from dual";

    @Override
    public String getValidationQuery() {
        return VALIDATION_QUERY;
    }

    @Override
    public String getDriverClassName() {
        return JDBC_DRIVER;
    }
}
