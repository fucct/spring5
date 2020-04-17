package spring.domain;

public class MemberSummaryPrinter extends MemberPrinter {
    @Override
    public void print(final Member member) {
        System.out.printf("회원 정보 : 이메일 = %s, 이름 = %s\n", member.getEmail(), member.getName());
    }
}
