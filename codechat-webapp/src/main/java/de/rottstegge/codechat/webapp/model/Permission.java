package de.rottstegge.codechat.webapp.model;

import de.rottstegge.codechat.webapp.model.base.AbstractBaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "app_permission")
public class Permission extends AbstractBaseEntity {

    private String name;

    public Permission() {
    }

    public Permission(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getId() {
        return doGetId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Permission that = (Permission) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
