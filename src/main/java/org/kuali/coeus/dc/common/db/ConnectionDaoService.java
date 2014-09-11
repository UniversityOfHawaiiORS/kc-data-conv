package org.kuali.coeus.dc.common.db;

import java.sql.Connection;

public interface ConnectionDaoService {

    Connection getCoeusConnection();
    Connection getRiceConnection();
}
