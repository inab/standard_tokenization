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
			JsonNode data = mapper.readTree(json_string);
			//JsonNode data = rootNode.path("chemical_entity");
			List<ChemicalCompound> myObjects = Arrays.asList(mapper.readValue(data.toString(), ChemicalCompound[].class));
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
