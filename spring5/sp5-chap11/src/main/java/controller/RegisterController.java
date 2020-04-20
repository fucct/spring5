package controller;

import domain.MemberRegisterService;
import domain.RegisterRequest;
import exception.DuplicateMemberException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller

public class RegisterController {

    private MemberRegisterService memberRegisterService;

    public void setMemberRegisterService(final MemberRegisterService memberRegisterService) {
        this.memberRegisterService = memberRegisterService;
    }

    @GetMapping("/register/step1")
    public String handleStep1() {
        return "register/step1";
    }

    @PostMapping("register/step2")
    public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") boolean agree, Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        if (agree) {
            return "register/step2";
        }
        return "register/step1";
    }

    @GetMapping("register/step2")
    public String getHandleStep2() {
        return "redirect:/register/step1";
    }

    @PostMapping("register/step3")
    public String handleStep3(@Valid RegisterRequest regReq, Errors errors) {
        if (errors.hasErrors()) {
            return "register/step2";
        }
        try {
            memberRegisterService.register(regReq);
            return "register/step3";
        } catch (DuplicateMemberException e) {
            errors.rejectValue("email", "duplicate");
            return "register/step2";
        }
    }

//    @InitBinder
//    protected void initBinder(WebDataBinder binder){
//        binder.setValidator(new RegisterRequestValidator());
//
//    }

}