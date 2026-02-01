package org.cms.com.domain;

public enum DocumentStatus {
    PENDING("Bekliyor"),
    APPROVED("Onaylandı"),
    REJECTED("Reddedildi");

    private final String displayName;

    DocumentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

