package org.kuali.coeus.dc.common.db;

public interface SequenceDaoService {

    String getNextRiceSequence(String sequenceName, String prefix);
    String getNextCoeusSequence(String sequenceName, String prefix);
}
