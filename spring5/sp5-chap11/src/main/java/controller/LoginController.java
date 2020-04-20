package controller;

import domain.AuthInfo;
import domain.AuthService;
import exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    public void setAuthService(final AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String form(LoginCommand loginCommand,
                       @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {
        if (rCookie != null) {
            loginCommand.setEmail(rCookie.getValue());
            loginCommand.setRememberEmail(true);
        }
        return "login/loginForm";
    }

    @PostMapping
    public String submit(@Valid LoginCommand loginCommand, Errors errors, HttpSession session,
                         HttpServletResponse response) {
        if (errors.hasErrors()) {
            return "login/loginForm";
        }
        try {
            AuthInfo authInfo = authService.authenticate(
                    loginCommand.getEmail(),
                    loginCommand.getPassword()
            );
            session.setAttribute("authInfo", authInfo);

            Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
            rememberCookie.setPath("/");
            if (loginCommand.isRememberEmail()) {
                rememberCookie.setMaxAge(60 * 60 * 24 * 30);
            } else {
                rememberCookie.setMaxAge(0);
            }
            response.addCookie(rememberCookie);

            return "login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "login/loginForm";
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new LoginCommandValidator());
    }
}
