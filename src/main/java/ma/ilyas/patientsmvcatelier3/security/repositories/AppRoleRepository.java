package ma.ilyas.patientsmvcatelier3.security.repositories;

import ma.ilyas.patientsmvcatelier3.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
