package org.kuali.coeus.dac.kim;

import java.util.Collection;

public interface RoleDao {

    void copyRoleMembersToDocAccessType(Collection<String> roleIds, KimAttributeDocumentValueHandler handler);
}
