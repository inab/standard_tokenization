package es.bsc.inb.limtox.daos.json;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.DocumentDao;
import es.bsc.inb.limtox.exceptions.MoreThanOneEntityException;
import es.bsc.inb.limtox.model.Document;
@Repository(value="documentJSONDao")
public class DocumentDaoJSONImpl extends GenericDaoJSONImpl<Document> implements DocumentDao{
	
	@Override
    public Document save(Document document) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String path = env.getProperty("limtox.input.folder");
			objectMapper.writeValue(new File(path+"pubmed_data/findings/baseline/1/"+document.getSourceId()+".json"), document);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
    }

	@Override
	public Document findBySourceId(String string) throws MoreThanOneEntityException {
		// TODO Auto-generated method stub
		return null;
	}

}
