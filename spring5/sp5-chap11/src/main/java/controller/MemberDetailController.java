package controller;

import domain.Member;
import domain.MemberDao;
import exception.MemberNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberDetailController {

    @Autowired
    private MemberDao memberDao;

    @GetMapping("/members/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Member member = memberDao.selectById(id);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException() {
        return "member/invalidId";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String handleMemberNotFoundException() {
        return "member/noMember";
    }
}
