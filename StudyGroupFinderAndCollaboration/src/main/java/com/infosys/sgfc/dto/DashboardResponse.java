package com.infosys.sgfc.dto;

public class DashboardResponse {

    private String fullName;
    private String email;
    private String department;
    private String skills;
    private String message;

    public DashboardResponse(String fullName, String email,
                             String department, String skills,
                             String message) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.skills = skills;
        this.message = message;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getSkills() { return skills; }
    public String getMessage() { return message; }
}
