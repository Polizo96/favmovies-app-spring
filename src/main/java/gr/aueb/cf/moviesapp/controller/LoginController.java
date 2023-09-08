package gr.aueb.cf.moviesapp.controller;

import gr.aueb.cf.moviesapp.authentication.CustomAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping(path = "/login")
    public String login(Principal principal, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute(CustomAuthenticationSuccessHandler.REDIRECT_URL, referer);

        return principal == null ? "login" : "redirect:/search";
    }

    @GetMapping(path = "/")
    public String root(Principal principal) {
        return principal == null ? "login" : "redirect:/search";
    }
}
