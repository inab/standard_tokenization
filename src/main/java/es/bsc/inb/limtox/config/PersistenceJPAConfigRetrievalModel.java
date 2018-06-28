package es.bsc.inb.limtox.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
public class PersistenceJPAConfigRetrievalModel{
   @Bean(name="retrievalEntityManagerFactory")
   public LocalContainerEntityManagerFactoryBean retrievalEntityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em 
        = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(retrievalDataSource());
      em.setPersistenceUnitName("retrievalPersistenceUnit");
      em.setPackagesToScan(new String[] { "es.bsc.inb.limtox.retrieval.model" });
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(retrievalAdditionalProperties());
      return em;
   }
 
   @Bean
   public DataSource retrievalDataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("org.sqlite.JDBC");
      dataSource.setUrl("jdbc:sqlite:/home/jcorvi/text_mining_data_test/bio_databases.db");
      dataSource.setUsername( "" );
      dataSource.setPassword( "" );
      return dataSource;
   }
 
   @Bean(name="transactionManager")
   public PlatformTransactionManager retrievalTransactionManager(EntityManagerFactory retrievalEntityManagerFactory){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(retrievalEntityManagerFactory);
       transactionManager.setPersistenceUnitName("retrievalPersistenceUnit");
       return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor retrievalExceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
   }
 
   Properties retrievalAdditionalProperties() {
       Properties properties = new Properties();
       properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
     //caution do not use in production becasuse will delete all the information 
       //properties.setProperty("hibernate.hbm2ddl.auto", "update");
       return properties;
   }
}
