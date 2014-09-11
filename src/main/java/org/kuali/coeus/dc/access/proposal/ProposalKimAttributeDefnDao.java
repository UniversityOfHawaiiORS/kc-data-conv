package org.kuali.coeus.dc.access.proposal;

public interface ProposalKimAttributeDefnDao {

    String getDocumentQualifierAttrDefnId();
    void deleteDocumentQualifierAttrDefn();
    void inactivateDocumentQualifierAttrDefn();
    boolean isDocumentQualifierAttrDefnUsed();
}
