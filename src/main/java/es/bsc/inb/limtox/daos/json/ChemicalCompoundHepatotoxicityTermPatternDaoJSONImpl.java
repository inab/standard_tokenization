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

import es.bsc.inb.limtox.daos.ChemicalCompoundHepatotoxicityTermPatternDao;
import es.bsc.inb.limtox.model.ChemicalCompoundHepatotoxicityTermPattern;


@Repository(value="chemicalCompoundHepatotoxicityTermPatternDaoJSONImpl")
public class ChemicalCompoundHepatotoxicityTermPatternDaoJSONImpl extends GenericDaoJSONImpl<ChemicalCompoundHepatotoxicityTermPattern> implements ChemicalCompoundHepatotoxicityTermPatternDao {



	@Override
    public List<ChemicalCompoundHepatotoxicityTermPattern> findAll() {
		ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.limtox_adverse_pattern"));
			JsonNode rootNode = mapper.readTree(json_string);
			JsonNode data = rootNode.path("limtox_adverse_pattern");
			List<ChemicalCompoundHepatotoxicityTermPattern> myObjects = Arrays.asList(mapper.readValue(data.toString(), ChemicalCompoundHepatotoxicityTermPattern[].class));
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
