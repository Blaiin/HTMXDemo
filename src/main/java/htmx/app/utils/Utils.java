package htmx.app.utils;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Component
public class Utils {

    public String encrypt (String password) {
        return Arrays.toString(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
    }

    public Object extract(Model model, String attribute){
        Objects.requireNonNull(model.getAttribute(attribute), "%s is null".formatted(attribute));
        return model.getAttribute(attribute);
    }
}
