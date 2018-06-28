package es.bsc.inb.limtox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import es.bsc.inb.limtox.services.MainServiceImpl;

@Configuration
@ComponentScan("es.bsc.inb.limtox")
public class AppConfig {

	@Bean
	MainServiceImpl mainServiceImpl() {
        return new MainServiceImpl();
    }
	
	
}
