package Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import DaoImpl.UserDaoImpl;
import Model.User;

public class HibernateConfig {
	@Autowired
	@Bean(name="datasource")
	public DataSource getH2Data()
	{
		System.out.println("hibernate bean initiated");
		DriverManagerDataSource dsource= new DriverManagerDataSource();
		dsource.setDriverClassName("org.h2.Driver");
		dsource.setUrl("jdbc:h2:~/test");
		dsource.setUsername("sa");
		dsource.setPassword("");
		System.out.println("H2 Connected");
		return dsource;
	}

	private Properties gethiberProp()
	{
		Properties p = new Properties();
		p.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		p.put("hibernate.hbm2ddl.auto", "update");
		p.put("hibernate.show_sql", "true");
		
		System.out.println("Data table created in H2");
		return p;
		
	}
	@SuppressWarnings("deprecation")
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFac(DataSource datasource)
	{	LocalSessionFactoryBuilder sb= new LocalSessionFactoryBuilder(datasource);
	  sb.addProperties(gethiberProp());
	  sb.addAnnotatedClass(User.class);
	  return sb.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name="UserDaoImpl")
	public UserDaoImpl getUseData(SessionFactory sf)
	{
		return new UserDaoImpl(sf);
	}
	
	@Autowired
	@Bean(name = "transcationManager")
	public HibernateTransactionManager gettrans(SessionFactory sf) {
		HibernateTransactionManager tm = new HibernateTransactionManager(sf);
		return tm;
	}
	

}
