package es.bsc.inb.limtox.daos.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.MarkerDao;
import es.bsc.inb.limtox.model.Marker;
@Repository(value="markerDaoJSONImpl")
public class MarkerDaoJSONImpl extends GenericDaoJSONImpl<Marker> implements MarkerDao{
	@Override
    public List<Marker> findAll() {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.liver_marker"));
			JsonNode rootNode = mapper.readTree(json_string);
			JsonNode data = rootNode.path("liver_marker");
			List<Marker> myObjects = mapper.readValue(data, new TypeReference<List<Marker>>(){});
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
