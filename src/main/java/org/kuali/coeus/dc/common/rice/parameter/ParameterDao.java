package org.kuali.coeus.dc.common.rice.parameter;

public interface ParameterDao {

    Parameter getParameter(ParameterKey key);
    void deleteParameter(ParameterKey key);
}
