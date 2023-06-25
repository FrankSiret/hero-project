package com.test.hero.heroproject.services.dto;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class HeroDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String superPower;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeroDTO)) return false;
        HeroDTO hero = (HeroDTO) o;
        return Objects.equals(getId(), hero.getId()) &&
                Objects.equals(getName(), hero.getName()) &&
                Objects.equals(getSuperPower(), hero.getSuperPower());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSuperPower());
    }

    @Override
    public String toString() {
        return "HeroDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", superPower='" + superPower + '\'' +
                '}';
    }
}
