package domain;

import exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {

    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPwd, newPwd);
        memberDao.update(member);
    }

    public void setMemberDao(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
