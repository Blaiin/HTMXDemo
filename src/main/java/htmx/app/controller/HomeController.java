package htmx.app.controller;

import htmx.app.dto.UserDTO;
import htmx.app.errors.exception.BusinessException;
import htmx.app.service.UserService;
import htmx.app.utils.Utils;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @PostMapping("/loginUser")
    public ResponseEntity<Void> login(@RequestParam("email") @NonNull String email,
                                      @RequestParam("password") @NonNull String password) {
        if(service.exists(email)) {
            try {
                if(service.authenticate(email, utils.encrypt(password))) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("HX-Redirect", "/home");
                    return ResponseEntity.ok().headers(headers).build();
                }
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    System.err.println("An error occurred while processing your request: " + e.getMessage());
                    e.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/signup")
    public String signup() {
        return "signupForm";
    }

    @PostMapping("/signupUser")
    public ResponseEntity<Void> signup(@RequestParam("username") @NonNull String username,
                                 @RequestParam("email") @NonNull String email,
                                 @RequestParam("password") @NonNull String password,
                                 @RequestParam("name") @NonNull String name,
                                 @RequestParam("surname") @NonNull String surname,
                                 @RequestParam("dateOfBirth") @NonNull String dateOfBirth) {
        if(!service.exists(email) && !service.exists(username)) {
            try {
                service.createUser(UserDTO.buildToPersist()
                                .username(username)
                                .email(email)
                                .password(utils.encrypt(password))
                                .name(name)
                                .surname(surname)
                                .dateOfBirth(dateOfBirth)
                                .build());
                System.out.println("User created successfully");
                HttpHeaders headers = new HttpHeaders();
                headers.add("HX-Redirect", "/login");
                return ResponseEntity.ok().headers(headers).build();
            } catch (BusinessException | NullPointerException e) {
                if (e instanceof BusinessException) {
                    System.err.println(((BusinessException) e).getExceptionReasoning());
                } else{
                    System.err.printf("An error occurred while processing your request: %s%n", (e.getMessage()));
                }
                HttpHeaders headers = new HttpHeaders();
                headers.add("HX-Redirect", "/error");
                return ResponseEntity.badRequest().headers(headers).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
