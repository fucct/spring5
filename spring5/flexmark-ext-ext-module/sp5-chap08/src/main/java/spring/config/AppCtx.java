package spring.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.domain.*;


@Configuration
@Import(DbConfig.class)
public class AppCtx {

    @Autowired
    private DataSource dataSource;

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource);
    }
}
