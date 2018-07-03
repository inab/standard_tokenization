package es.bsc.inb.limtox.daos.jpa;

import org.springframework.stereotype.Repository;

import es.bsc.inb.limtox.daos.CytochromeDao;
import es.bsc.inb.limtox.model.Cytochrome;
@Repository(value="cytochromeDaoJPAImpl")
public class CytochromeDaoJPAImpl extends GenericDaoJPAImpl<Cytochrome> implements CytochromeDao{

	
}
