package spring.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {

    @Autowired
    private MemberDao memberDao;
    @Autowired
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

    public void setMemberDao(final MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setPrinter(final MemberPrinter printer) {
        this.printer = printer;
    }
}
