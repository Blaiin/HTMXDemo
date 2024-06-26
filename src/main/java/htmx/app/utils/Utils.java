package htmx.app.utils;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Component
public class Utils {

    public String encrypt (String password) {
        return Arrays.toString(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
    }

    public String extract(Model model, String attribute){
        return Objects.requireNonNull(model.getAttribute(attribute), "%s is null".formatted(attribute)).toString();
    }
    public static class DateUtils {
        public static final String DATE_FORMAT = "dd-MM-yyyy";
        public static final DateTimeFormatter LOCALE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

        public static <T> LocalDate formatDate(T s) throws IllegalArgumentException{
            if (s instanceof String t) {
                if(t.matches("\\d{2}-\\d{2}-\\d{4}")) return LocalDate.of(Integer.parseInt(t.substring(6, 10)),
                                                                                Integer.parseInt(t.substring(3, 5)),
                                                                                Integer.parseInt(t.substring(0, 2)));
                if(t.matches("\\d{4}-\\d{2}-\\d{2}")) return LocalDate.of(Integer.parseInt(t.substring(0, 4)),
                                                                                Integer.parseInt(t.substring(5, 7)),
                                                                                Integer.parseInt(t.substring(8, 10)));
            }
            if(s instanceof LocalDate date) return date;
            throw new IllegalArgumentException("Unsupported date format: %s; Date format must be -> dd-MM-yyyy".formatted(s));
        }
    }
}
