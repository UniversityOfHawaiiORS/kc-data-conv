package org.kuali.coeus.dac.kim;

import java.util.Collection;

public interface RoleDao {

    void copyRolesToDocAccessType(Collection<String> roleIds, KimAttributeDocumentValueHandler handler);
}
