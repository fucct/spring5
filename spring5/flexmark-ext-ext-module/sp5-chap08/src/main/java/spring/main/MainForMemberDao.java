package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.AppCtx;
import spring.domain.Member;
import spring.domain.MemberDao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainForMemberDao {
    private static MemberDao memberDao;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        memberDao = ctx.getBean(MemberDao.class);

        selectAll();
        updateMember();
        insertMember();
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

    private static void insertMember() {
        System.out.println("---------insertMember()");
        String prefix = formatter.format(LocalDateTime.now());
        Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
        memberDao.insert(member);
        System.out.println(member.getId() + " 데이터 추가");
    }

    private static void updateMember() {
        System.out.println("---------updateMember()");
        Member member = memberDao.selectByEmail("dqrd123@gmail.com");
        String oldPw = member.getPassword();
        String newPw = "c940429kk!!";
        member.changePassword(oldPw, newPw);

        memberDao.update(member);
        System.out.println("암호 변경 : " + oldPw + " -> " + newPw);
    }

    private static void selectAll() {
        System.out.println("---------selectAll()");
        int total = memberDao.count();
        System.out.println("전체 데이터 : " + total);
        List<Member> members = memberDao.selectAll();
        for (Member member : members) {
            System.out.println(member.getId() + " : " + member.getEmail() + " : " + member.getName());
        }
    }
}
