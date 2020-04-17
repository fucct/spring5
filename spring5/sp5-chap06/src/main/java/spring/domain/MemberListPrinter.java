package spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("listPrinter")
public class MemberListPrinter {

    private MemberDao memberDao;
    private MemberPrinter memberPrinter;

    public MemberListPrinter() {
    }

    public MemberListPrinter(final MemberDao memberDao, final MemberPrinter memberPrinter) {
        this.memberDao = memberDao;
        this.memberPrinter = memberPrinter;
    }

    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(m -> memberPrinter.print(m));
    }

    @Autowired
    public void setMemberDao(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    @Qualifier("printer")
    public void setMemberPrinter(final MemberPrinter memberPrinter) {
        this.memberPrinter = memberPrinter;
    }
}
