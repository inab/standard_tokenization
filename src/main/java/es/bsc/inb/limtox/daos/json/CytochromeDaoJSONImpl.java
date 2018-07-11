package es.bsc.inb.limtox.daos.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.CytochromeDao;
import es.bsc.inb.limtox.model.Cytochrome;
@Repository(value="cytochromeDaoJSONImpl")
public class CytochromeDaoJSONImpl extends GenericDaoJSONImpl<Cytochrome> implements CytochromeDao{
	@Override
    public List<Cytochrome> findAll() {
    	ObjectMapper mapper = new ObjectMapper();
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.p450_cytochrome"));
			JsonNode rootNode = mapper.readTree(json_string);
			//JsonNode data = rootNode.path("p450_cytochrome");
			List<Cytochrome> myObjects = mapper.readValue(rootNode, new TypeReference<List<Cytochrome>>(){});
			return myObjects;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

}
