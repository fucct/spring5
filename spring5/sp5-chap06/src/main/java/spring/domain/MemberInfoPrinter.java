package spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("infoPrinter")
public class MemberInfoPrinter {

    private MemberDao memberDao;
    private MemberPrinter printer;

    public void printMemberInfo(String email) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) {
            System.out.println("존재하지 않는 이메일입니다.");
            return;
        }

        printer.print(member);
        System.out.println();
    }

    @Autowired
    public void setMemberDao(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Autowired
    @Qualifier("printer")
    public void setPrinter(final MemberPrinter printer) {
        this.printer = printer;
    }
}
