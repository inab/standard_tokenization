package es.bsc.inb.limtox.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import es.bsc.inb.limtox.retrieval.model.PubMedArticle;
import es.bsc.inb.limtox.retrieval.services.GenericRetrievalService;

@PropertySource({ "classpath:limtox.properties" })
@Service
public class MainServiceImpl {
	@Autowired
    private Environment env;
	@Autowired
	StandardTokenizationService standardTokenizationService;
	@Autowired
	GenericRetrievalService genericRetrievalService;
	@Autowired
	DictionaryService dictionaryService;

	public void execute() {
		System.out.println("Comienzo");
		long start=0;
		long stop=0;
		start = System.nanoTime();
		String work_path = env.getProperty("limtox.input.folder");
		dictionaryService.execute();
		List<PubMedArticle>  pubMedArticles = genericRetrievalService.findAllPubMedArticleToProcess();
		int i=0;
		for (PubMedArticle pubMedArticle : pubMedArticles) {
			i=i+1;
			try {
				String input_path = work_path+"/pubmed/standardization/"+pubMedArticle.getFilePath();
				if (Files.isRegularFile(Paths.get(input_path+"/"+pubMedArticle.getFileName()+".xml"))) {
					String output_path = work_path+"/pubmed/findings/"+pubMedArticle.getFilePath();
					Boolean success=true;
					if (!Files.isDirectory(Paths.get(output_path))) {
						 success = (new File(output_path)).mkdirs();
					}
					if(success) {
						standardTokenizationService.execute(pubMedArticle.getPmid(), input_path+"/"+pubMedArticle.getFileName()+".xml",  output_path, pubMedArticle);
						
					}else {
						//error in creation of output directory
						pubMedArticle.setTokenizationProcessed(false);
					}
				}else {
					pubMedArticle.setTokenizationProcessed(false);
				}
			}catch(Exception e) {
				e.printStackTrace();
				pubMedArticle.setTokenizationProcessed(false);
			}
			genericRetrievalService.save(pubMedArticle);
		}
		stop = System.nanoTime();
		System.out.println("Seconds: " + (stop-start)/1000000000.0);
	}
	
//	public void execute() {
//		long start=0;
//		long stop=0;
//		start = System.nanoTime();
//		dictionaryService.execute();
//		File root = new File("/home/jcorvi/text_mining_data_test/pubmed_data/standardization/baseline/2");
//		for (File  file : root.listFiles(new FileFilterXML())) { 
//			try {
//				String pmid = file.getName().substring(4, file.getName().indexOf('.'));
//				//standardTokenizationService.execute(pmid,file.getAbsolutePath());
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//		stop = System.nanoTime();
//		System.out.println("Seconds: " + (stop-start)/1000000000.0);
//	}


}
