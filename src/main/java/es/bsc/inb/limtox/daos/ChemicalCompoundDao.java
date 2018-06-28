package es.bsc.inb.limtox.daos;

import java.util.HashMap;

import es.bsc.inb.limtox.model.ChemicalCompound;


public interface ChemicalCompoundDao extends GenericDao<ChemicalCompound>{

	public HashMap<String,ChemicalCompound> findAllAsHash() ;
	
}
