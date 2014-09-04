package org.kuali.coeus.dac.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionService {

    Connection getConnection(String connectionString) throws SQLException;
}
