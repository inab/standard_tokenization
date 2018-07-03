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

import es.bsc.inb.limtox.daos.ChemicalCompoundDao;
import es.bsc.inb.limtox.model.ChemicalCompound;
@Repository(value="chemicalCompoundDaoJSONImpl")
public class ChemicalCompoundDaoJSONImpl extends GenericDaoJSONImpl<ChemicalCompound> implements ChemicalCompoundDao{
	
	@Override
    public List<ChemicalCompound> findAll() {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.chemical_entity"));
			JsonNode rootNode = mapper.readTree(json_string);
			JsonNode data = rootNode.path("chemical_entity");
			List<ChemicalCompound> myObjects = mapper.readValue(data, new TypeReference<List<ChemicalCompound>>(){});
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
	public ChemicalCompound save(ChemicalCompound t) {
		// TODO Auto-generated method stub
		return null;
	}

}
