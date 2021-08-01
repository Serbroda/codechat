package de.rottstegge.codechat.webapp.model.shared;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Roles {
    ADMIN("ADMIN", new HashSet<>(Arrays.asList(Permissions.values())));

    private final String name;
    private final Set<Permissions> permissions;

    Roles(String name, Set<Permissions> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

}
