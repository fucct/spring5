package spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import spring.domain.*;

@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtx {

    @Bean
    public MemberDao memberdao(){
        return new MemberDao();
    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
