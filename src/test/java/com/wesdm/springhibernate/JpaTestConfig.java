package com.wesdm.springhibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Unit Tests use Annotation Configuration approach so I can do both
 * @author Wesley
 *
 */
@Configuration		//indicates that the class can be used by the Spring IoC container as a source of bean definitions
@ComponentScan(basePackages = "com.wesdm.springhibernate")		//loads beans @service, @ repository, etc.
@PropertySource("classpath:dbexample.properties")
@EnableTransactionManagement
public class JpaTestConfig {
	
	//Gets value from db properties file
	//if we don’t need profile’s information, we should use @Value annotation
	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	
	//if we need profile’s information, we should inject Environment from context
//	@Autowired
//	private Environment env;
	
	static final Logger LOG = LoggerFactory.getLogger(JpaTestConfig.class);

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
    	
    	LOG.info("F'ING DIALECT = "+hibernateDialect);

        LocalContainerEntityManagerFactoryBean lcemfb
            = new LocalContainerEntityManagerFactoryBean();

        lcemfb.setDataSource(this.dataSource());
        lcemfb.setPackagesToScan(new String[] {"com.wesdm.springhibernate"});
        //lcemfb.setPersistenceUnitName("MyTestPU");

        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        lcemfb.setJpaVendorAdapter(va);

        Properties ps = new Properties();
        ps.put("hibernate.dialect", hibernateDialect);
        ps.put("hibernate.hbm2ddl.auto", "create-drop");
        //ps.put("hibernate.dialect", env.getProperty("hibernate.dialect"));		//gets property from Environment object

        lcemfb.setJpaProperties(ps);

        lcemfb.afterPropertiesSet();

        return lcemfb;

    }
    
    @Bean JdbcTemplate jdbcTemplateBean(){
    	return new JdbcTemplate(this.dataSource());
    }

    @Bean
    public DataSource dataSource(){

    	return new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
                addScript("sql/setup.sql").
                build();

    }

    @Bean
    public PlatformTransactionManager transactionManager(){

        JpaTransactionManager tm = new JpaTransactionManager();

        tm.setEntityManagerFactory(
            this.entityManagerFactoryBean().getObject() );

        return tm;

    }
    
    /**
     * Used to translate HibernateException, PersistenceException, etc. into Spring DataAccessException
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    /**
     * To resolve ${} in @Value
     * @return
     */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}