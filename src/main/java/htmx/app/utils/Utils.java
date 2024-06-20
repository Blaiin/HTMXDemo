package htmx.app.utils;

import htmx.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Component
public class Utils {
    @Autowired
    private UserRepository userRepository;
    public boolean inDatabase(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public String encrypt (String password) {
        return Arrays.toString(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
    }
}
