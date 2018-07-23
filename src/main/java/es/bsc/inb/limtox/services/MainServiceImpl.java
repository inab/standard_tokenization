package es.bsc.inb.limtox.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.retrieval.services.GenericRetrievalService;
import es.bsc.inb.limtox.util.file.FileFilterTxt;

@PropertySource({ "classpath:limtox.properties" })
@Service
public class MainServiceImpl {
	@Autowired
    private Environment env;
	@Autowired
	StandardTokenizationServiceImpl standardTokenizationService;
	@Autowired
	GenericRetrievalService genericRetrievalService;
	@Autowired
	DictionaryService dictionaryService;
	@Autowired
	SectionService sectionService;
//	public void execute() {
//		dictionaryService.execute();
//		sectionService.execute();
//		List<PubMedArticle>  pubMedArticles = genericRetrievalService.findAllPubMedArticleToProcess();
//		for (PubMedArticle pubMedArticle : pubMedArticles) {
//			try {
//				standardTokenizationService.execute(pubMedArticle.getPmid(), env.getProperty("limtox.input.folder")+"/pubmed_data/standardization/"+pubMedArticle.getFilePath()+"/"+pubMedArticle.getFileName()+".txt");
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public void execute() {
		long start=0;
		long stop=0;
		start = System.nanoTime();
		dictionaryService.execute();
		//sectionService.execute();
		File root = new File("/home/jcorvi/text_mining_data_test/pubmed_data/standardization/baseline/2");
		for (File  file : root.listFiles(new FileFilterTxt())) { 
			try {
				String pmid = file.getName().substring(4, file.getName().indexOf('.'));
				standardTokenizationService.execute(pmid,file.getAbsolutePath());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		stop = System.nanoTime();
		System.out.println("Seconds: " + (stop-start)/1000000000.0);
	}


}
