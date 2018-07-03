package es.bsc.inb.limtox.daos.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundInhibitionPatternDao;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInhibitionPattern;


@Repository(value="cytochromeChemicalCompoundInhibitionPatternDaoJSONImpl")
public class CytochromeChemicalCompoundInhibitionPatternDaoJSONImpl extends GenericDaoJSONImpl<CytochromeChemicalCompoundInhibitionPattern> implements CytochromeChemicalCompoundInhibitionPatternDao {

	/**
	 * 
	 */
	public List<CytochromeChemicalCompoundInhibitionPattern> findAll() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.limtox_p450_cyps_inhibition_pattern"));
			JsonNode rootNode = mapper.readTree(json_string);
			JsonNode data = rootNode.path("limtox_p450_cyps_inhibition_pattern");
			List<CytochromeChemicalCompoundInhibitionPattern> myObjects = mapper.readValue(data, new TypeReference<List<CytochromeChemicalCompoundInhibitionPattern>>(){});
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
