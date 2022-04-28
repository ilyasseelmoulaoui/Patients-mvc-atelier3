package ma.ilyas.patientsmvcatelier3.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data //pour les getters et les setters
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    private String userId;
    @Column(unique=true)
    private String username;
    private String password;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER) //FetchType.Lazy ça veut dire que à chaque fois on veut charger un user à partir de la BD, hibernate va charger automat. les roles de cet utilisateur en memoire.
    //FetchType.Lazy est le contraire de EAGER
    private List<AppRole> appRoles = new ArrayList<>();

}
