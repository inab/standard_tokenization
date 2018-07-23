package es.bsc.inb.limtox.daos.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.bsc.inb.limtox.daos.HepatotoxicityTermDao;
import es.bsc.inb.limtox.model.HepatotoxicityTerm;
@Repository(value="hepatotoxicityTermDaoJSONImpl")
public class HepatotoxicityTermDaoJSONImpl extends GenericDaoJSONImpl<HepatotoxicityTerm> implements HepatotoxicityTermDao{
	
	@Override
    public List<HepatotoxicityTerm> findAll() {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.hepatotoxicity"));
			JsonNode data = mapper.readTree(json_string);
			//JsonNode data = rootNode.path("hepatotoxicity");
			List<HepatotoxicityTerm> myObjects = Arrays.asList(mapper.readValue(data.toString(), HepatotoxicityTerm[].class));
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
    public void saveAll(List<HepatotoxicityTerm> hepatotoxicityTerms) {
    	ObjectMapper mapper = new ObjectMapper();
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.hepatotoxicity"));
			JsonNode rootNode = mapper.readTree(json_string);
			JsonNode data = rootNode.path("hepatotoxicity");
			//List<HepatotoxicityTerm> myObjects = mapper.readValue(data.textValue(), new TypeReference<List<HepatotoxicityTerm>>(){});
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
	}
	
}
