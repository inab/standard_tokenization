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

import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundPatternDao;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundMetabolismPattern;


@Repository(value="cytochromeChemicalCompoundPatternDaoJSONImpl")
public class CytochromeChemicalCompoundPatternDaoJSONImpl extends GenericDaoJSONImpl<CytochromeChemicalCompoundMetabolismPattern> implements CytochromeChemicalCompoundPatternDao {



	
	@Override
    public List<CytochromeChemicalCompoundMetabolismPattern> findAll() {
		ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.limtox_cyps_pattern"));
			JsonNode data = mapper.readTree(json_string);
			//JsonNode data = rootNode.path("limtox_cyps_pattern");
			List<CytochromeChemicalCompoundMetabolismPattern> myObjects = Arrays.asList(mapper.readValue(data.toString(), CytochromeChemicalCompoundMetabolismPattern[].class));
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
