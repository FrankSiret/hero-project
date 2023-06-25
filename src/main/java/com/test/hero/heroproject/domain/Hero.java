package com.test.hero.heroproject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hero")
public class Hero implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence_generator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "super_power")
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
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
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
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", superPower='" + superPower + '\'' +
                '}';
    }
}
