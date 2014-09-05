package org.kuali.coeus.dac.db;

public interface SequenceDaoService {

    String getNextRiceSequence(String sequenceName, String prefix);
    String getNextCoeusSequence(String sequenceName, String prefix);
}
