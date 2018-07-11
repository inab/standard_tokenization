package es.bsc.inb.limtox.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.bsc.inb.limtox.config.AppConfig;
import es.bsc.inb.limtox.services.MainServiceImpl;

class Main {

    public static void main(String[] args) {
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        MainServiceImpl mainService = (MainServiceImpl)ctx.getBean("mainServiceImpl");
        mainService.execute();
    }
}
