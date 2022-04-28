package ma.ilyas.patientsmvcatelier3.security.service;

import ma.ilyas.patientsmvcatelier3.security.entities.AppRole;
import ma.ilyas.patientsmvcatelier3.security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String rePassword);
    AppRole saveNewRole(String roleName, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
}
