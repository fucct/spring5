package spring5.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring5.domain.chap07.Calculator;
import spring5.domain.chap07.RecCalculator;

public class MainAspect {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator calculator = ctx.getBean("calculator2", RecCalculator.class);
        System.out.println(calculator.factorial(10));
        System.out.println(calculator.getClass());

    }
}
