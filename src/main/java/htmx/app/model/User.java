package htmx.app.model;

import htmx.app.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "dob", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "activeSince")
    private LocalDateTime activeSince;

    public User (String username,
                 String email,
                 String password,
                 String name,
                 String surname,
                 LocalDate dateOfBirth,
                 LocalDateTime activeSince) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.activeSince = activeSince;
    }

    public static User fromDTO(UserDTO dto) {
        return new User(dto.getUsername(),
                        dto.getEmail(),
                        dto.getPassword(),
                        dto.getName(),
                        dto.getSurname(),
                        dto.getDateOfBirth(),
                        dto.getActiveSince());
    }

}
