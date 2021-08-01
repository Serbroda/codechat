package de.rottstegge.codechat.webapp.repository;

import de.rottstegge.codechat.webapp.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByNameIgnoreCase(String name);

    Set<Permission> findAllByNameIn(List<String> names);
}
