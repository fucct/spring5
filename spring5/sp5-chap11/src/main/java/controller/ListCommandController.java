package controller;

import domain.Member;
import domain.MemberDao;
import exception.MemberNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ListCommandController {

    @Autowired
    private MemberDao memberDao;

    @RequestMapping("/members")
    public String list(@ModelAttribute("cmd") @Valid ListCommand listCommand, Errors errors, Model model) {
        if (!errors.hasErrors()) {

            List<Member> members = memberDao.selectByRegisterDate(listCommand.getFrom(), listCommand.getTo());
            model.addAttribute("members", members);
        }
        return "member/memberList";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ListCommandValidator());
    }
}
