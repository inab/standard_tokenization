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

import es.bsc.inb.limtox.daos.CytochromeChemicalCompoundInductionPatternDao;
import es.bsc.inb.limtox.model.CytochromeChemicalCompoundInductionPattern;


@Repository(value="cytochromeChemicalCompoundInductionPatternDaoJSONImpl")
public class CytochromeChemicalCompoundInductionPatternDaoJSONImpl extends GenericDaoJSONImpl<CytochromeChemicalCompoundInductionPattern> implements CytochromeChemicalCompoundInductionPatternDao {

	@Override
    public List<CytochromeChemicalCompoundInductionPattern> findAll() {
		ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		try {
			String json_string = unzipDictionary(env.getProperty("limtox.dictionary.limtox_p450_cyps_induction_pattern"));
			JsonNode data = mapper.readTree(json_string);
			//JsonNode data = rootNode.path("limtox_p450_cyps_induction_pattern");
			List<CytochromeChemicalCompoundInductionPattern> myObjects = Arrays.asList(mapper.readValue(data.toString(), CytochromeChemicalCompoundInductionPattern[].class));
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
