package ma.ilyas.patientsmvcatelier3;

import ma.ilyas.patientsmvcatelier3.entities.Patient;
import ma.ilyas.patientsmvcatelier3.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcAtelier3Application {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcAtelier3Application.class, args);
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
}
