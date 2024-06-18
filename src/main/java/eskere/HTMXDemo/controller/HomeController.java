package eskere.HTMXDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // This is the controller method that will be called when the user navigates to the root URL
    // It returns the name of the view that should be rendered
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/signup")
    public String signup() {
        return "signupForm";
    }
}
