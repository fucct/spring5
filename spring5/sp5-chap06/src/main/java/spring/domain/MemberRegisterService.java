package spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.exception.DuplicateMemberException;

import java.time.LocalDateTime;

@Component("regSvc")
public class MemberRegisterService {

    @Autowired
    private MemberDao memberDao;

    public MemberRegisterService() {
    }

    public MemberRegisterService(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long register(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("중복된 email : " + req.getEmail());
        }
        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();
    }
}
