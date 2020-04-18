package spring.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("C940429kk!!");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(10);
        dataSource.setTestWhileIdle(true);  // 유휴 커넥션 검사
        dataSource.setMinEvictableIdleTimeMillis(1000*60*3);    // 최소 유휴 시간 3분
        dataSource.setTimeBetweenEvictionRunsMillis(1000*10);   //10초 주기
        return dataSource;
    }
}
