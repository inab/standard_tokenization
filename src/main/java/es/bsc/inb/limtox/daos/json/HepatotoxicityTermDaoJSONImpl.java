package es.bsc.inb.limtox.daos.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.HepatotoxicityTermDao;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
@Repository
public class HepatotoxicityTermDaoJSONImpl extends GenericDaoJSONImpl<HepatotoxicityTerm> implements HepatotoxicityTermDao{

	
    @Override
    public List<HepatotoxicityTerm> findAll() {
    	ObjectMapper mapper = new ObjectMapper();
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.hepatotoxicity"));
			JsonNode rootNode = mapper.readTree(json_string);
			JsonNode data = rootNode.path("hepatotoxicity");
			List<HepatotoxicityTerm> myObjects = mapper.readValue(data, new TypeReference<List<HepatotoxicityTerm>>(){});
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

	@Override
	public HashMap<String, HepatotoxicityTerm> findAllAsHash() {
		// TODO Auto-generated method stub
		return null;
	}
}
