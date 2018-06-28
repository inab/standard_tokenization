package es.bsc.inb.limtox.daos;

import java.util.List;

import es.bsc.inb.limtox.model.LimtoxEntity;

public interface GenericDao<T extends LimtoxEntity> {
    
	public T create(T t);

    public void delete(Object id);

    public T find(Object id);

    public T update(T t);   
    
    public List<T> findAll();
    
    public T save(T t);
}