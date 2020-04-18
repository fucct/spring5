#sp5-chap08
(공부 내용 정리 [Github](https://github.com/fucct/TIL)
### JDBC 의 단점 보완

Spring은 JDBC 의 중복되는 코드를 제거하기 위한 JdbcTemplate 클래스를 제공한다. 또한 트랜잭션 관리가 쉽다.

### DataSource 설정

JDBC는 DriverManager 외에 DataSource를 통해 DB연결을 구할 수 있다. 사용할 DataSource를 스프링 빈으로 등록하고, 주입하여 사용한다

javax.sql.DataSource(인터페이스) → tomcat.jdbc.DataSource(구현 객체)

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

Tomcat JDBC 모듈의 DataSource 는 커넥션 풀 기능을 제공하는 클래스이다

### JdbcTemplate 클래스의 query() 메서드

select 쿼리 실행을 위한 query() 메서드 제공

query() 메서드는 sql 파라미터로 쿼리를 실행하고, RowMapper를 통해 ResultSet의 결과를 자바 객체로 변환한다. 만약 쿼리문에 ? 가 존재하면 갯수만큼 인자를 전달한다.

    public interface RowMapper<T> {
    	T mapRow(ResultSet rs, int rowNum) throws SQLException;
    }

### 쿼리 결과가 1행인 경우 사용하는 queryForObject() 메서드

select 결과가 1행인 경우엔 queryForObject()를 사용한다.

    public int count(){
    	Integer count = jdbcTemplate.queryForObject(
    		"select count(*) from Member", Integer.class);
    	return count;
    }

두번째 파라미터는 칼럼을 읽어올 때 사용할 타입을 지정. 물론 인덱스 파라미터 또한 지정 가능하다. 만약 1행이 아니면 IncorrectResultSizeDataAccessException 발생

### JdbcTemplate 클래스의 update() 메서드

insert, update, delete 쿼리는 update() 메서드를 이용한다. update 메서드의 결과로 변경된 행의 개수를 리턴한다.

### PreparedStatementCreator를 이용한 쿼리 실행

위의 방법처럼 인덱스 파라미터로 인자를 전달하지 않고, PreparedStatement의 set메서드를 통해 값을 설정해야 할 때도 있다.

    public interface PreparedStatementCreator {
    	PreparedStatement createPreparedStatement(Connection con) throws SQLException;
    }

### Insert 실행 시 KeyHolder를 이용해 자동 생성 키 값 구하기

    public void insert(final Member member) {
    	KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcTemplate.update(PreparedStatementCreator, keyHolder);
    	Number keyValue = keyHolder.getKey();
    	member.setId(keyValue.longValue());
    } 

update 메서드의 2번째 인자로 KeyHolder를 주면 자동 생성된 키 값을 KeyHolder에 보관한다.

### 스프링의 익셉션 변환 처리

JdbcTemplate 메서드에서 SQLException이 발생하면, 이 익셉션을 알맞은 RuntimeException으로 변환해서 발생시킨다. → 연동 기술에 상관 없이 (Jdbc, JPA, Hibernate 등) 익셉션을 동일하게 처리하기 위함이다.

Runtime Exception 이기 때문에 필요한 경우에만 처리해주면 된다.

### 트랜잭션 처리

트랜잭션이란 두개 이상의 쿼리를 한 작업으로 묶어주는 기능을 한다. 묶인 쿼리중 하나라도 실패하면 전체 쿼리를 실패로 간주하고 롤백한다. 또한 모든 쿼리가 성공해서 실제로 DB에 반영하는데, 이를 커밋이라고 한다.

JDBC는 setAutoCommit(false)→ commit or rollback 을 통해 트랜잭션 처리를 한다.

### @Transactional을 이용한 트랜잭션 처리

@Transactional 이 붙은 메서드는 동일한 트랜잭션 범위에서 실행된다.

- PlatformTransactionManager 빈 설정
- @Transactional 애노테이션 활성화 설정

    @Configuration
    @EnableTransactionManagement
    public class AppCtx {

    	...
    	
    	@Bean
    	public PlatformTransactionManager transactionManager() {
    		DataSourceTransactionManager tm = new DataSourceTransactionManager();
    		tm.setDataSource(dataSource());
    		return tm;
    	}
    }

### @Transactional 과 프록시

AOP에서와 같이 트랜잭션도 공통 기능중 하나다. 스프링은 @Transactional을 처리하기 위해 내부적으로 AOP를 사용한다. @EnableTransactionManagement 태그는 Transactional 애노테이션이 적용된 빈 객체를 찾아 알맞은 프록시 객체를 생성한다.

RuntimeException의 경우 롤백, 그러나 SQLException의 경우 롤백하지 않는다. 만약 롤백을 원하면

    @Transactional(rollbackFor = SQLException.class)

반대로 롤백을 원하지 않으면 noRollbackFor 속성을 사용한다.

### @Transactional의 주요 속성

- value(String) : 트랜잭션 관리 PlatformTransactionManager 빈 이름, default = " "
- propagation(Propagation) : 트랜잭션 전파 타입, default = Propagation.REQUIRED
- isolation(Isolation) : 트랜잭션 격리 레벨, default=Isolation.DEFAULT
- timeout(int) : 트랜잭션 제한 시간(초), default=-1

### @EnableTransactionManagement의 주요 속성

- proxyTargetClass : 클래스를 프록시로 할 지, 인터페이스를 할 지
- order : AOP 적용 순서

### 트랜잭션 전파

JdbcTemplate는 진행 중인 트랜잭션이 존재하면 해당 트랜잭션 범위에서 쿼리를 실행한다.