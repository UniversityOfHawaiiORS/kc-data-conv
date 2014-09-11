package org.kuali.coeus.dc.access.kim;

public class RoleMemberAttributeData {

    private String id;
    private String roleMemberId;
    private String attributeValue;
    private String kimAttributeId;
    private String kimTypeId;
    private Long versionNumber;
    private String objectId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleMemberId() {
        return roleMemberId;
    }

    public void setRoleMemberId(String roleMemberId) {
        this.roleMemberId = roleMemberId;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getKimAttributeId() {
        return kimAttributeId;
    }

    public void setKimAttributeId(String kimAttributeId) {
        this.kimAttributeId = kimAttributeId;
    }

    public String getKimTypeId() {
        return kimTypeId;
    }

    public void setKimTypeId(String kimTypeId) {
        this.kimTypeId = kimTypeId;
    }

    public Long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
