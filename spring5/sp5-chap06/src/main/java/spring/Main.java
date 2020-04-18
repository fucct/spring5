package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        Client2 client = ctx.getBean("client2", Client2.class);
        client.send();
    }
}
