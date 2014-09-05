package org.kuali.coeus.dac.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionDaoService {

    Connection getCoeusConnection();
    Connection getRiceConnection();
}
