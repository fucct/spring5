package config;

import controller.*;
import domain.MemberRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import survey.SurveyController;

@Configuration
@Import(MemberConfig.class)
public class ControllerConfig {

    @Autowired
    private MemberRegisterService memberRegSvc;

    @Bean
    public RegisterController registerController() {
        RegisterController registerController = new RegisterController();
        registerController.setMemberRegisterService(memberRegSvc);
        return registerController;
    }

    @Bean
    public SurveyController surveyController() {
        return new SurveyController();
    }

    @Bean
    public LoginController loginController() {
        return new LoginController();
    }

    @Bean
    public LogoutController logoutController() {
        return new LogoutController();
    }

    @Bean
    public ChangePwdController changePwdController() {
        return new ChangePwdController();
    }

    @Bean
    public ListCommandController listCommandController() {
        return new ListCommandController();
    }

    @Bean
    public MemberDetailController memberDetailController() {
        return new MemberDetailController();
    }

    @Bean
    public IndexController indexController() {
        return new IndexController();
    }
}
