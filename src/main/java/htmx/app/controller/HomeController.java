package htmx.app.controller;

import htmx.app.model.User;
import htmx.app.service.UserService;
import htmx.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
public class HomeController {
    private final Utils utils;
    private final UserService service;

    @Autowired
    public HomeController (Utils utils, UserService userService) {
        this.utils = utils;
        this.service = userService;
    }

    @GetMapping("/")
    public String landing() {
        return "redirect:login";
    }
    @GetMapping("/login")
    public String home(){
        return "index";
    }
    @GetMapping("/signup")
    public String signup() {
        return "signupForm";
    }
    @PostMapping("/signup")
    public String signup(Model model) {
        String username = Objects.requireNonNull(model.getAttribute("username")).toString();
        String password = Objects.requireNonNull(model.getAttribute("password")).toString();
        String email = Objects.requireNonNull(model.getAttribute("email")).toString();
        String name = Objects.requireNonNull(model.getAttribute("name")).toString();
        String surname = Objects.requireNonNull(model.getAttribute("surname")).toString();
        LocalDate dob = Objects.requireNonNull((LocalDate) model.getAttribute("dob"));
        if(utils.inDatabase(username)) {
            service.createUser(User.builder()
                                .username(username)
                                .email(email)
                                .password(utils.encrypt(password))
                                .name(name)
                                .surname(surname)
                                .dateOfBirth(dob)
                                .activeSince(LocalDateTime.now())
                    .build());
            return "redirect:homepage";
        }
        return "signup";
    }
}
