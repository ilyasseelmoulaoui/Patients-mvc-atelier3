package ma.ilyas.patientsmvcatelier3.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ilyas.patientsmvcatelier3.security.entities.AppRole;
import ma.ilyas.patientsmvcatelier3.security.entities.AppUser;
import ma.ilyas.patientsmvcatelier3.security.repositories.AppRoleRepository;
import ma.ilyas.patientsmvcatelier3.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
@Slf4j //parfois si on veut logger on utilise @Slf4j ça permet de logger (les infos)
@AllArgsConstructor //pour faire l'injection des dependance , (ou bien utiliser @Autorwired, mais cette derniere n'est pas recommandée pour des raisons de securité)
@Transactional // quandd on fait appel a une methode de cette classe au debut de la meyhode il fait begin transaction et à lsa fin il fait commit,et alors toute les midf qui sont fait il va faire update
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("Passwords not match");
        String hashedPwd = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUserId(UUID.randomUUID().toString()); //puisque userID est String, donc il faut le generer, et cette methode permet de generer un Id aleatoire qui depend de la date systeme ce qui fait qu'il est unique
        appUser.setUsername(username);
        appUser.setPassword(hashedPwd);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole!=null) throw new RuntimeException("Role "+roleName+" Already exists");
        appRole = new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedAppRole =  appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
        //On a pas besoin de faire save() car il va automatiquement l'ajouter dans la table d'assocation mais les methode doivent etre @Transactional
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("User not found");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

}
