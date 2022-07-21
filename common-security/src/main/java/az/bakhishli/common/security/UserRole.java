package az.bakhishli.common.security;

public enum UserRole {
    ROLE_ANONYMOUS,
    ROLE_USER, //user that has just registered in the web site, not using any services
    ROLE_ADMIN, //manages all info in the platform
    ROLE_ORGANIZATION_USER,
    ROLE_SUPER_USER; //I haven't decided on this yet :)

}
