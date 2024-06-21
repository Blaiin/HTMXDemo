package htmx.app.controller;

import htmx.app.dto.UserDTO;
import htmx.app.errors.exception.BusinessException;
import htmx.app.service.UserService;
import htmx.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;


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
        if(!service.exists(utils.extract(model, "email").toString())) {
            try {
                service.createUser(UserDTO.build(utils.extract(model, "username").toString(),
                                                    utils.extract(model, "email").toString(),
                                                    utils.encrypt(utils.extract(model, "password").toString()),
                                                    utils.extract(model, "name").toString(),
                                                    utils.extract(model, "surname").toString(),
                                                    utils.extract(model, "dateOfBirth"),
                                                    LocalDateTime.now()));

                return "redirect:homepage";
            } catch (BusinessException | NullPointerException e) {
                if (e instanceof BusinessException) {
                    System.err.println(((BusinessException) e).getExceptionReasoning());
                } else{
                    System.err.printf("An error occurred while processing your request: %s%n", (e.getMessage()));
                }
                return "redirect:error";
            }
        }
        return "signup";
    }
}
