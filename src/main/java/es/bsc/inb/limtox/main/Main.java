package es.bsc.inb.limtox.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.bsc.inb.limtox.config.AppConfig;
import es.bsc.inb.limtox.services.MainServiceImpl;

class Main {

    public static void main(String[] args) {
//    	
//    	String sent = ".Casein-sepharose 4b other Casein-sepharose 4b casein-sepharose 4b microRNAs as potential , casein-sepharose 4b@.";
//		//contiene 
//    	//String chemicalCompoundValue =  ".*casein-Sepharose 4B.*";
//    	
//    	//String chemicalCompoundValue =  ".*[\\s]casein-Sepharose 4B[\\s].*";
//    	
//    	//hace el match solo si contiene el string y no esta rodeado de una letra o numero A word character, short for [a-zA-Z_0-9] 
//    	//String chemicalCompoundValue =  ".*[\\W]casein-sepharose 4b[\\W].*";
//		//falta cuando no hay mas nada o salto de linea, osea puede o no haber caracter.
//    	String to_search = Pattern.quote("casein-sepharose 4b");
//    	//to_search = "casein-sepharose 4b";
//    	//String chemicalCompoundValue =  "(^|(.|\\s)*[\\W|\\s|^])"+to_search+"([\\W|\\s|$](.|\\s)*|$)";
//    	
//    	
//    	//String chemicalCompoundValue =  "[\\W|\\s+|^]"+to_search+"[\\W|\\s+|$]";
//    	String chemicalCompoundValue =  "(\\W|\\s|^)"+to_search+"(\\W|\\s|$)";
//    	
//    	//String chemicalCompoundValue =  ".*[\\W]casein-sepharose 4b(([\\W|\\s](.|\\s)*)|$)";
//    	Pattern p = Pattern.compile(chemicalCompoundValue);
//		Matcher m = p.matcher(sent.toLowerCase());
//		//Boolean b = m.matches();
//		Integer count = 0;
//		while (m.find())
//		    count++;
    	
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        MainServiceImpl mainService = (MainServiceImpl)ctx.getBean("mainServiceImpl");
        mainService.execute();
    }
}
