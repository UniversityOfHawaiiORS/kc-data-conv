package org.kuali.coeus.dac.dao.impl;

import org.kuali.coeus.dac.dao.ConnectionService;

public abstract class AbstractConnectionService implements ConnectionService {

    public abstract String getDriverClass();
}
