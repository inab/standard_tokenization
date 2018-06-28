package es.bsc.inb.limtox.daos.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundPatternDao;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundPattern;


@Repository
public class CytochromeChemicalCompoundPatternDaoImpl extends GenericDaoJSONImpl<CytochromeChemicalCompoundPattern> implements CytochromeChemicalCompoundPatternDao {

	/**
	 * 
	 */
	public List<CytochromeChemicalCompoundPattern> findAll() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.limtox_cyps_pattern"));
			JsonNode rootNode = mapper.readTree(json_string);
			JsonNode data = rootNode.path("limtox_cyps_pattern");
			List<CytochromeChemicalCompoundPattern> myObjects = mapper.readValue(data, new TypeReference<List<CytochromeChemicalCompoundPattern>>(){});
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
