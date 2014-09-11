package org.kuali.coeus.dc.access.kim;

import java.util.Collection;

public interface RoleDao {

    void copyRoleMembersToDocAccessType(Collection<String> roleIds, KimAttributeDocumentValueHandler handler);
}
