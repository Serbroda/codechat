package de.rottstegge.codechat.webapp.service.impl;

import de.rottstegge.codechat.webapp.model.Permission;
import de.rottstegge.codechat.webapp.model.Role;
import de.rottstegge.codechat.webapp.model.shared.Permissions;
import de.rottstegge.codechat.webapp.model.shared.Roles;
import de.rottstegge.codechat.webapp.repository.PermissionRepository;
import de.rottstegge.codechat.webapp.repository.RoleRepository;
import de.rottstegge.codechat.webapp.service.DataInitializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataInitializerImpl implements DataInitializer {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public DataInitializerImpl(PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialize() {
        initializePermissions();
        initializeRoles();
    }

    private void initializePermissions() {
        for (Permissions p : Permissions.values()) {
            Permission permission = permissionRepository.findByNameIgnoreCase(p.getName());
            if (permission == null) {
                permission = new Permission(p.getName());
                permissionRepository.save(permission);
            }
        }
    }

    private void initializeRoles() {
        for (Roles r : Roles.values()) {
            Role role = roleRepository.findByNameIgnoreCase(r.getName());
            List<String> collect = r.getPermissions().stream().map(Permissions::getName).collect(Collectors.toList());
            if (role == null) {
                role = new Role(r.getName());
                role.setPermissions(permissionRepository.findAllByNameIn(collect));
                roleRepository.save(role);
            } else if (!hasRoleAllPermissions(role, r.getPermissions())) {
                role.setPermissions(permissionRepository.findAllByNameIn(collect));
                roleRepository.save(role);
            }
        }
    }

    private boolean hasRoleAllPermissions(Role role, Set<Permissions> permissions) {
        if (role == null || (permissions == null && (role.getPermissions() == null || role.getPermissions().isEmpty()))) {
            return true;
        }
        List<String> rolePermissionNames = role.getPermissions().stream().map(p -> p.getName().toLowerCase(Locale.ROOT)).collect(Collectors.toList());
        List<String> permissionNames = permissions.stream().map(p -> p.getName().toLowerCase(Locale.ROOT)).collect(Collectors.toList());
        return rolePermissionNames.containsAll(permissionNames);
    }
}
