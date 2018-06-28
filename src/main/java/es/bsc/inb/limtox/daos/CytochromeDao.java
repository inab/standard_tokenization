package es.bsc.inb.limtox.daos;

import java.util.HashMap;

import es.bsc.inb.limtox.model.Cytochrome;


public interface CytochromeDao extends GenericDao<Cytochrome>{

	public HashMap<String,Cytochrome> findAllAsHash() ;
	
}
