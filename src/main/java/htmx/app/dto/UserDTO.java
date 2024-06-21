package htmx.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(staticName = "build")
public class UserDTO {

    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private LocalDateTime activeSince;

    public UserDTO (String username, String email, String password, String name, String surname, LocalDateTime activeSince) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.activeSince = activeSince;
    }

    public static UserDTO build(String username,
                                String email,
                                String password,
                                String name,
                                String surname,
                                Object dateOfBirth,
                                LocalDateTime activeSince) {
        UserDTO dto = new UserDTO(username, email, password, name, surname, activeSince);
        dto.setDateOfBirth(dateOfBirth);
        return dto;
    }
    private void setDateOfBirth(Object dateOfBirth) {
        if(dateOfBirth instanceof LocalDate) this.dateOfBirth = (LocalDate) dateOfBirth;
        if(dateOfBirth instanceof String) this.dateOfBirth = LocalDate.parse((String) dateOfBirth);
    }


}
