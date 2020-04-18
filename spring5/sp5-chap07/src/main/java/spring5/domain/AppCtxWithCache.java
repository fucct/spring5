package spring5.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import spring5.domain.chap07.Calculator;
import spring5.domain.chap07.RecCalculator;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache {

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect();
    }

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}
