package es.bsc.inb.limtox.daos;

import java.util.HashMap;

import es.bsc.inb.limtox.model.HepatotoxicityTerm;


public interface HepatotoxicityTermDao extends GenericDao<HepatotoxicityTerm>{

	public HashMap<String,HepatotoxicityTerm> findAllAsHash() ;
	
}
