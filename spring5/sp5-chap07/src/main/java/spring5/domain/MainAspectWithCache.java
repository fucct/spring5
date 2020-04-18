package spring5.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring5.domain.chap07.Calculator;

public class MainAspectWithCache {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCache.class);
        Calculator cal = ctx.getBean("calculator", Calculator.class);
        cal.factorial(10);
        cal.factorial(7);
        cal.factorial(10);
        cal.factorial(7);
        cal.factorial(5);
        cal.factorial(5);
        cal.factorial(5);

    }
}
