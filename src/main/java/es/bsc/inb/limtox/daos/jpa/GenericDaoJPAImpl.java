package es.bsc.inb.limtox.daos.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.bsc.inb.limtox.daos.GenericDao;
import es.bsc.inb.limtox.model.LimtoxEntity;


@Transactional(readOnly=false)
@Repository(value="genericDaoJPAImpl")
public abstract class GenericDaoJPAImpl<T extends LimtoxEntity> implements GenericDao<T> {

    @PersistenceContext(unitName="modelPersistenceUnit")
    protected EntityManager em;

    private Class<T> type;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoJPAImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T create( T t) {
        this.em.persist(t);
        return t;
    }

    @Override
    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }

    @Override
    @Transactional(readOnly=true)
    public T find(final Object id) {
        return (T) this.em.find(type, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public List<T> findAll() {
    	Collection<T> collection = em.createQuery("from " + type.getName() + " x").getResultList();
    	return new ArrayList<T> (collection);
    }
    
    @Override
    public T update(final T t) {
        return this.em.merge(t);    
    }
    
    @Override
    public T save(final T t) {
    	if(t.getId()==null || t.getId()==0) {
    		this.em.persist(t);
    		return t;
    	}else {
    		return this.em.merge(t);    
    	}
    }
    
}
