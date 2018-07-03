package es.bsc.inb.limtox.daos.jpa;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.ChemicalCompoundDao;
import es.bsc.inb.limtox.model.ChemicalCompound;
@Repository(value="chemicalCompoundDaoJPAImpl")
public class ChemicalCompoundDaoJPAImpl extends GenericDaoJPAImpl<ChemicalCompound> implements ChemicalCompoundDao{

}
