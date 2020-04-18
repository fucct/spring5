package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.AppCtx;
import spring.domain.ChangePasswordService;
import spring.exception.MemberNotFoundException;
import spring.exception.WrongIdPasswordException;

public class MainForCPS {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        ChangePasswordService pwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
        try{
            pwdSvc.changePassword("dqrd123@gmail.com", "c940429kk!?", "c940429kk!!");
            System.out.println("암호 변경 완료!");
        } catch (MemberNotFoundException e){
            System.out.println("회원 데이터가 존재하지 않습니다.");
        } catch (WrongIdPasswordException e){
            System.out.println("암호가 올바르지 않습니다.");
        }
    }
}
