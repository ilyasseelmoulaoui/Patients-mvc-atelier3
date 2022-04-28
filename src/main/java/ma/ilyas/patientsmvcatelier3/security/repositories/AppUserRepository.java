package ma.ilyas.patientsmvcatelier3.security.repositories;

import ma.ilyas.patientsmvcatelier3.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
