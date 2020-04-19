package controller;

import domain.AuthInfo;
import domain.ChangePasswordService;
import exception.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {

    @Autowired
    private ChangePasswordService changePwdSvc;

    @GetMapping
    public String form(@ModelAttribute("command") ChangePwdCommand pwdCmd){
        return "edit/changePwdForm";
    }

    @PostMapping
    public String submit(@ModelAttribute("command") @Valid ChangePwdCommand pwdCmd, Errors errors, HttpSession session){
        if (errors.hasErrors()) {
            return "edit/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
        try {
            changePwdSvc.changePassword(
                    authInfo.getEmail(),
                    pwdCmd.getOldPassword(),
                    pwdCmd.getNewPassword()
            );
            return "edit/changedPwd";
        } catch (WrongIdPasswordException e) {
            errors.rejectValue("oldPassword", "notMatching");
            return "edit/changePwdForm";
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new ChangePwdCommandValidator());
    }
}
