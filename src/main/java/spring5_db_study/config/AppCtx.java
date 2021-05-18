package spring5_db_study.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring5_db_study.spring.ChangePasswordService;
import spring5_db_study.spring.MemberDao;
import spring5_db_study.spring.MemberInfoPrinter;
import spring5_db_study.spring.MemberListPrinter;
import spring5_db_study.spring.MemberPrinter;
import spring5_db_study.spring.MemberRegisterService;
import spring5_db_study.spring.VersionPrinter;

@Configuration
@ComponentScan(basePackages = {"spring5_db_study.spring"})
@EnableTransactionManagement
public class AppCtx {
	
	@Bean(destroyMethod= "close")
	public DataSource dataSource() {
		DataSource ds= new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5?useSSL=false");
		ds.setUsername("user_spring5");
		ds.setPassword("rootroot");
		ds.setInitialSize(2);	// 초기 커넥션 개수. 기본값은 10
		ds.setMaxIdle(10);		// 커넥션을 유지할 수 있는 최대 커넥션 개수.
		ds.setMaxActive(10);	// 커넥션풀에서 가져올 수 있는 최대 커넥션 개수. 기본값은 100
		ds.setTestWhileIdle(true);	// 커넥션 풀에 유휴 상태로 있는 동안에 검사여부. 기본값 false
		ds.setMinEvictableIdleTimeMillis(100*60 * 3); 	// 최소유휴시간3분
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10); 	// 10초주기
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	

}
