package kissat.ruokintaseuranta.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/kirjaudu")
    public String login() {
        return "kirjaudu"; 
    }

    @GetMapping("/kirjauduulos")
    public String logout() {
        return "redirect:/kirjaudu?kirjauduulos"; 
    }
}

