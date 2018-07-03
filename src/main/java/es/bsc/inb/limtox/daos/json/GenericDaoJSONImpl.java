package es.bsc.inb.limtox.daos.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.bsc.inb.limtox.daos.GenericDao;
import es.bsc.inb.limtox.model.LimtoxEntity;

@PropertySource({ "classpath:limtox.properties" })
@Repository(value="genericDaoJSONImpl")
public abstract class GenericDaoJSONImpl<T extends LimtoxEntity> implements GenericDao<T> {
	
	@Autowired
    Environment env;
	
    private Class<T> type;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoJSONImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }
    
    /**
	 * 
	 * @throws IOException
	 */
	protected String unzipDictionary(String file_path) throws IOException {
		try{
			FileInputStream in = new FileInputStream(file_path);
			GZIPInputStream gis = new GZIPInputStream(in);
			//String s = IOUtils.toString(gis);
			BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		    StringBuilder textBuilder = new StringBuilder();
		    String line;
		    while ((line=bf.readLine())!=null) {
		    	textBuilder.append(line);
		    }
		    bf.close();
		    gis.close();
		    in.close();
		    return textBuilder.toString();
	    }catch(IOException ex){
	       ex.printStackTrace();   
	    }
		return null;
	}
	
	@Override
    public T create( T t) {
        return null;
    }

    @Override
    public void delete(final Object id) {
        
    }

    @Override
    @Transactional(readOnly=true)
    public T find(final Object id) {
        return null;
    }

    @Override
    @Transactional(readOnly=true)
    public List<T> findAll() {
    	return null;
    }
    
    @Override
    public T update(final T t) {
    	return null;   
    }
    
	@Override
    public T save(final T document) {
    	return null;
    }
    
	

}
