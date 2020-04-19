package config;

import domain.AuthService;
import domain.ChangePasswordService;
import domain.MemberDao;
import domain.MemberRegisterService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class MemberConfig {

    @Bean
    public DataSource dataSource(){
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        ds.setUsername("root");
        ds.setPassword("C940429kk!!");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setMinEvictableIdleTimeMillis(60000 * 3);
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    public MemberDao memberDao(){
        return new MemberDao(dataSource());
    }

    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePwdSvc(){
        return new ChangePasswordService();
    }

    @Bean
    public AuthService authService() {
        return new AuthService();
    }
}
