package org.genc.app.SneakoAplication.enums;

public enum RoleType {
    ROLE_ADMIN("Administrator Role"),
    ROLE_DEV("Standard User Role"),
    ROLE_CUSTOMER("Developer  JAVA FSE");


    private final String description;

    RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return this.name();
    }
}
