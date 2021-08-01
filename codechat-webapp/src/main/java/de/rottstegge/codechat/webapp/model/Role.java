package de.rottstegge.codechat.webapp.model;

import de.rottstegge.codechat.webapp.model.base.AbstractBaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app_role")
public class Role extends AbstractBaseEntity {

    private String name;
    private Set<de.rottstegge.codechat.webapp.model.Permission> permissions = new HashSet<>();

    public Role() {
    }

    public Role(String name) {
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

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<de.rottstegge.codechat.webapp.model.Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<de.rottstegge.codechat.webapp.model.Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
