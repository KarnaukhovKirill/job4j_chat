package ru.job4j.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonDTO {
    @Size(min = 2, message = "Username must be greater then 2")
    private String username;
    @Size(min = 6, message = "Username must be greater then 6")
    private String password;
    @NotNull(message = "Please, enter person's id")
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
