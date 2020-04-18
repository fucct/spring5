package spring5.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring5.domain.chap07.Calculator;
import spring5.domain.chap07.IterCalculator;
import spring5.domain.chap07.RecCalculator;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator1() {
        return new IterCalculator();
    }

    @Bean
    public Calculator calculator2() {
        return new RecCalculator();
    }
}
