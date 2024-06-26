package htmx.app.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static htmx.app.utils.Utils.DateUtils.LOCALE_FORMATTER;
import static htmx.app.utils.Utils.DateUtils.formatDate;

public record UserDTO (
    String username,
    String email,
    String password,
    String name,
    String surname,
    LocalDate dateOfBirth,
    LocalDateTime activeSince) {

    @Builder(builderMethodName = "buildToPersist")
    public UserDTO(String username,
                    String email,
                    String password,
                    String name,
                    String surname,
                    String dateOfBirth) {
        this(username,
                email,
                password,
                name,
                surname,
                formatDate(dateOfBirth),
                LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Builder(builderMethodName = "userDTOBuilder")
    public UserDTO(String username,
                   String email,
                   String password,
                   String name,
                   String surname,
                   LocalDate dateOfBirth,
                   String activeSince) {
        this(username,
                email,
                password,
                name,
                surname,
                formatDate(dateOfBirth),
                LocalDateTime.parse(activeSince));
    }


}
