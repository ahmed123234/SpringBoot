package com.example.studentapp.security;

public enum ApplicationUserPermission {
    STUDENT_READ("student:raed"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String Permission;

    ApplicationUserPermission(String permission) {
        this.Permission = permission;
    }

    public String getPermission() {
        return Permission;
    }
}
