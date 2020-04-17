package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.AppCtx;
import spring.domain.*;
import spring.exception.DuplicateMemberException;
import spring.exception.MemberNotFoundException;
import spring.exception.WrongIdPasswordException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {

    private static ApplicationContext ctx;

    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력해 주세요 : ");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            }
            if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            }
            if (command.equalsIgnoreCase("list")) {
                processListCommand();
                continue;
            }
            if (command.startsWith("info ")) {
                processInfoCommand(command.split(" "));
                continue;
            }
            if (command.equalsIgnoreCase("version")) {
                processVersionCommand();
                continue;
            }
            printHelp();
        }
    }

    private static void processVersionCommand() {
        VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    private static void processInfoCommand(final String[] arg) {
        if (arg.length != 2) {
            printHelp();
            return;
        }
        MemberInfoPrinter memberInfoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        memberInfoPrinter.printMemberInfo(arg[1]);
    }

    private static void processListCommand() {
        MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }

    private static void processChangeCommand(final String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }
        ChangePasswordService pwdSvc = ctx.getBean("pwdSvc", ChangePasswordService.class);
        try {
            pwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호 변경 완료!\n");
        } catch (MemberNotFoundException me) {
            System.out.println("존재하지 않는 이메일입니다!");
        } catch (WrongIdPasswordException we) {
            System.out.println("이메일과 패스워드가 일치하지 않습니다.");
        }
    }

    private static void printHelp() {
        System.out.println("1. new email name password confirm-password");
        System.out.println("2. change email old-password new-password");
        System.out.println("3. exit");
    }

    private static void processNewCommand(final String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return;
        }
        MemberRegisterService regSvc = ctx.getBean("regSvc", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("확인 비밀번호가 일치하지 않습니다.");
            return;
        }
        try {
            regSvc.register(req);
            System.out.println("등록 완료!\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재하는 아이디입니다.\n" + e.getMessage());
        }
    }
}
