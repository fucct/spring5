//package spring.main;
//
//
//import spring.domain.Assembler;
//import spring.domain.ChangePasswordService;
//import spring.domain.MemberRegisterService;
//import spring.domain.RegisterRequest;
//import spring.exception.DuplicateMemberException;
//import spring.exception.MemberNotFoundException;
//import spring.exception.WrongIdPasswordException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class MainForAssembler {
//
//    private static final Assembler assembler = new Assembler();
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
//            System.out.println("명령어를 입력해 주세요 : ");
//            String command = reader.readLine();
//            if (command.equalsIgnoreCase("exit")) {
//                System.out.println("프로그램을 종료합니다.");
//                break;
//            }
//            if (command.startsWith("new ")) {
//                processNewCommand(command.split(" "));
//                continue;
//            }
//            if (command.startsWith("change ")) {
//                processChangeCommand(command.split(" "));
//                continue;
//            }
//            printHelp();
//        }
//    }
//
//    private static void processChangeCommand(final String[] arg) {
//        if (arg.length != 4) {
//            printHelp();
//            return;
//        }
//        ChangePasswordService pwdSvc = assembler.getPwdSvc();
//        try {
//            pwdSvc.changePassword(arg[1], arg[2], arg[3]);
//            System.out.println("암호 변경 완료!\n");
//        } catch (MemberNotFoundException me) {
//            System.out.println("존재하지 않는 이메일입니다!");
//        } catch (WrongIdPasswordException we) {
//            System.out.println("이메일과 패스워드가 일치하지 않습니다.");
//        }
//    }
//
//    private static void printHelp() {
//        System.out.println("1. new email name password confirm-password");
//        System.out.println("2. change email old-password new-password");
//        System.out.println("3. exit");
//    }
//
//    private static void processNewCommand(final String[] arg) {
//        if (arg.length != 5) {
//            printHelp();
//            return;
//        }
//        MemberRegisterService regSvc = assembler.getRegSvc();
//        RegisterRequest req = new RegisterRequest();
//        req.setEmail(arg[1]);
//        req.setName(arg[2]);
//        req.setPassword(arg[3]);
//        req.setConfirmPassword(arg[4]);
//
//        if (!req.isPasswordEqualToConfirmPassword()) {
//            System.out.println("확인 비밀번호가 일치하지 않습니다.");
//            return;
//        }
//        try {
//            regSvc.register(req);
//            System.out.println("등록 완료!\n");
//        } catch (DuplicateMemberException e) {
//            System.out.println("이미 존재하는 아이디입니다.\n" + e.getMessage());
//        }
//    }
//}
