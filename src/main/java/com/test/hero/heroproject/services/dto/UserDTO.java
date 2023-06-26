package com.test.hero.heroproject.services.dto;

import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class UserDTO {

    private Integer id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    private String username;

    private Set<String> authorities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO user = (UserDTO) o;
        return
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authorities='" + authorities + '\'' +
                '}';
    }
}
