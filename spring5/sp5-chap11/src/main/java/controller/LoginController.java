package controller;

import domain.AuthInfo;
import domain.AuthService;
import exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String form(LoginCommand loginCommand){
        return "login/loginForm";
    }

    @PostMapping
    public String submit(@Valid LoginCommand loginCommand, Errors errors, HttpSession session){
        if(errors.hasErrors()){
            return "login/loginForm";
        }
        try {
            AuthInfo authInfo = authService.authenticate(
                    loginCommand.getEmail(),
                    loginCommand.getPassword()
            );
            session.setAttribute("authInfo", authInfo);
            return "login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "login/loginForm";
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new LoginCommandValidator());
    }
}
