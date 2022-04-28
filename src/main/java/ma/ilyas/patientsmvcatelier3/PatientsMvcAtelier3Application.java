package ma.ilyas.patientsmvcatelier3;

import ma.ilyas.patientsmvcatelier3.entities.Patient;
import ma.ilyas.patientsmvcatelier3.repositories.PatientRepository;
import ma.ilyas.patientsmvcatelier3.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcAtelier3Application {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcAtelier3Application.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Ahmed", new Date(), false, 60));
            patientRepository.save(new Patient(null,"Hanae", new Date(), true, 45));
            patientRepository.save(new Patient(null,"Marwane", new Date(), true, 48));
            patientRepository.save(new Patient(null,"Rihab", new Date(), false, 88));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("Rami","1234","1234");
            securityService.saveNewUser("Khaoula","1234","1234");
            securityService.saveNewUser("Hassan","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("Rami","USER");
            securityService.addRoleToUser("Rami","ADMIN");
            securityService.addRoleToUser("Khaoula","USER");
            securityService.addRoleToUser("Hassan","USER");
        };
    }
}
