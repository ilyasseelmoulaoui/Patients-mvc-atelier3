package ma.ilyas.patientsmvcatelier3.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data   @AllArgsConstructor @NoArgsConstructor
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //puisque roleId est de type Long on utilise @GeneratedValue...
    private Long roleId;
    @Column(unique=true)
    private String roleName;
    private String description;
}
