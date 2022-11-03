package com.hepta.persistence;

import com.hepta.entity.Setor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SetorDAO {

	public void create(Setor setor) throws Exception {
		EntityManager db = HibernateUtil.getEntityManager();
		try {
			db.getTransaction().begin();
			db.persist(setor);
			db.getTransaction().commit();
		} catch(Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Setor> getAll() throws Exception{ 
		EntityManager db = HibernateUtil.getEntityManager();
		List<Setor> result = new ArrayList<Setor>();
		try {
			db.getTransaction().begin();
			Query selectSQL = db.createQuery("FROM Setor");
			result = selectSQL.getResultList();
		} catch(Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}
		return result;
	}
	
	public Setor update(Setor setor) throws Exception{
		EntityManager db = HibernateUtil.getEntityManager();
		Setor setorAtualizado = null;
		try {
			db.getTransaction().begin();
			setorAtualizado = db.merge(setor);
			db.getTransaction().commit();
		} catch (Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}
		return setorAtualizado;
	}
	
	public Setor find(Integer id) throws Exception {
		EntityManager db = HibernateUtil.getEntityManager();
		Setor setor = null;
		try {
			setor = db.find(Setor.class, id);
			
		} catch (Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}
		return setor;
		
	}
	
	public void delete(Integer id) throws Exception{
		EntityManager db = HibernateUtil.getEntityManager();
		try {
			db.getTransaction().begin();
			Setor setor = db.find(Setor.class, id);
			db.remove(setor);
			db.getTransaction().commit();
		}catch (Exception e) {
			db.getTransaction().rollback();		
			throw new Exception(e);
		} finally {
			db.close();
		}
	}
}
