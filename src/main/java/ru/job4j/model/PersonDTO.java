package ru.job4j.model;

public class PersonDTO {
    private String username;
    private String password;
    private int roleId;

    public PersonDTO() {
    }

    public PersonDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "PersonDTO{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", roleId=" + roleId
                + '}';
    }
}
