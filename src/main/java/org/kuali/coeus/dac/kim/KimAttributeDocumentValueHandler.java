package org.kuali.coeus.dac.kim;

public interface KimAttributeDocumentValueHandler {

    String transform(String val);
    boolean isDocumentValueType(String attrDefnId);
}
