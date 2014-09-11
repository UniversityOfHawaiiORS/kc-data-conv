package org.kuali.coeus.dc.access.kim;

public interface KimAttributeDocumentValueHandler {

    String transform(String val);
    boolean isDocumentValueType(String attrDefnId);
    void cleanup();
}
