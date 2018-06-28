package es.bsc.inb.limtox.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({ "classpath:databases.properties" })
@EnableTransactionManagement
public class PersistenceJPAConfig{
	
	@Autowired
    private Environment env; 
	
	@Bean(name="modelEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean modelEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "es.bsc.inb.limtox.model" });
		em.setPersistenceUnitName("modelPersistenceUnit");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(this.additionalProperties());
		return em;
	}
 
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("limtox.jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("limtox.jdbc.url"));
		dataSource.setUsername( env.getProperty("limtox.jdbc.user") );
		dataSource.setPassword( env.getProperty("limtox.jdbc.pass") );
		return dataSource;
	}
 
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory modelEntityManagerFactory){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(modelEntityManagerFactory);
		transactionManager.setPersistenceUnitName("modelPersistenceUnit");
		return transactionManager;
	}
 
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
 
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("limtox.hibernate.dialect"));
		//caution do not use in production becasuse will delete all the information 
		//properties.setProperty("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
}
