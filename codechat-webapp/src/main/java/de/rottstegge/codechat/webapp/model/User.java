package de.rottstegge.codechat.webapp.model;

import de.rottstegge.codechat.webapp.model.base.AbstractBaseEntity;
import de.rottstegge.codechat.webapp.model.shared.UserState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "app_user")
public class User extends AbstractBaseEntity {

    private String username;
    private String password;
    private de.rottstegge.codechat.webapp.model.Contact contact = new de.rottstegge.codechat.webapp.model.Contact();
    private UserState state = UserState.REGISTERED;

    private Set<Role> roles = new HashSet<>();
    private Set<de.rottstegge.codechat.webapp.model.Permission> permissions = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public long getId() {
        return doGetId();
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

    @Embedded
    public de.rottstegge.codechat.webapp.model.Contact getContact() {
        return contact;
    }

    public void setContact(de.rottstegge.codechat.webapp.model.Contact contact) {
        this.contact = contact;
    }

    @Enumerated(EnumType.STRING)
    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<de.rottstegge.codechat.webapp.model.Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<de.rottstegge.codechat.webapp.model.Permission> permissions) {
        this.permissions = permissions;
    }

    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role r : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName()));
            for (de.rottstegge.codechat.webapp.model.Permission p : r.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            }
        }
        for (de.rottstegge.codechat.webapp.model.Permission p : permissions) {
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        }
        return authorities;
    }
}