package de.rottstegge.codechat.webapp.repository;

import de.rottstegge.codechat.webapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNameIgnoreCase(String name);
}
