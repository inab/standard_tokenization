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

import es.bsc.inb.limtox.daos.MarkerChemicalCompoundPatternDao;
import es.bsc.inb.limtox.model.MarkerChemicalCompoundPattern;


@Repository(value="markerChemicalCompoundPatternDaoJSONImpl")
public class MarkerChemicalCompoundPatternDaoJSONImpl extends GenericDaoJSONImpl<MarkerChemicalCompoundPattern> implements MarkerChemicalCompoundPatternDao {
	
	@Override
    public List<MarkerChemicalCompoundPattern> findAll() {
		ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.limtox_marker_pattern"));
			JsonNode data = mapper.readTree(json_string);
			data = data.path("limtox_marker_pattern");
			List<MarkerChemicalCompoundPattern> myObjects = Arrays.asList(mapper.readValue(data.toString(), MarkerChemicalCompoundPattern[].class));
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
