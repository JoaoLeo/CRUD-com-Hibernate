package com.hepta.persistence;

import com.hepta.entity.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class FuncionarioDAO {

	public void create(Funcionario funcionario) throws Exception {
		EntityManager db = HibernateUtil.getEntityManager();
		try {
			db.getTransaction().begin();
			db.persist(funcionario);
			db.getTransaction().commit();
		} catch (Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> getAll() throws Exception {
		EntityManager db = HibernateUtil.getEntityManager();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			db.getTransaction().begin();
			Query selectAll = db.createQuery("FROM Funcionario f JOIN FETCH f.setor"); // SETOR É UMA PROPRIEDADE
																							// DE FUNCIONARIO POR ISSO
																							// REFERENCIA F.SETOR
			funcionarios = selectAll.getResultList();
			db.getTransaction().commit();
		} catch (Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}

		return funcionarios;
	}

	public Funcionario update(Funcionario funcionario) throws Exception {
		EntityManager db = HibernateUtil.getEntityManager();
		Funcionario funcionarioAtualizado = null;
		try {
			db.getTransaction().begin();
			funcionarioAtualizado = db.merge(funcionario);
			db.getTransaction().commit();
		} catch (Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}

		return funcionarioAtualizado;
	}

	public Funcionario find(int id) throws Exception {
		EntityManager db = HibernateUtil.getEntityManager();
		Funcionario funcionario = null;
		try {
			db.getTransaction().begin();
			Query find = db.createQuery("FROM Funcionario f JOIN FETCH f.setor WHERE Id_FUNCIONARIO = " + id);
			funcionario = (Funcionario) find.getSingleResult();
			db.getTransaction().commit();
		} catch (Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}

		return funcionario;
	}

	public void delete(Integer id) throws Exception {
		EntityManager db = HibernateUtil.getEntityManager();
		try {
			db.getTransaction().begin();
			Funcionario funcionario = db.find(Funcionario.class, id);
			db.remove(funcionario);
			db.getTransaction().commit();
		} catch (Exception e) {
			db.getTransaction().rollback();
			throw new Exception(e);
		} finally {
			db.close();
		}
	}
}
