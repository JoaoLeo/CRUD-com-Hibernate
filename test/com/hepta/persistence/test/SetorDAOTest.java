package com.hepta.persistence.test;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.hepta.entity.Setor;
import com.hepta.persistence.SetorDAO;

public class SetorDAOTest {
	private static Setor setor;
	private static SetorDAO dao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		setor = new Setor();
		dao = new SetorDAO();
	}

	@Test
	void testCreate() {
		setor.setNome("Teste2");
		try {
			dao.create(setor);
		} catch (Exception e) {
			System.out.print("Erro ao salvar, erro: " + e);
		}
	}

	@Test
	void testGetAll() {
		try {
			List<Setor> setor = dao.getAll();
			for (Setor s : setor) {
				System.out.println("ID:" + s.getId());
				System.out.println("Nome:" + s.getNome());
				System.out.println();
			}
		} catch (Exception e) {
			System.out.print("Erro ao buscar, erro: " + e);
		}
	}

	@Test
	void testUpdate() {
		setor.setId(5);
		setor.setNome("Teste");
		try {
			dao.update(setor);
		} catch (Exception e) {
			System.out.print("Erro ao atualizar, erro: " + e);
		}
	}

	@Test
	void testFind() {
		try {
			setor = dao.find(1);
		} catch (Exception e) {
			System.out.print("Erro ao buscar, erro: " + e);
		}
		System.out.println("ID:" + setor.getId());
		System.out.println("Nome:" + setor.getNome());
	}

	@Test
	void testDelete() {
		try {
			dao.delete(5);
		} catch (Exception e) {
			System.out.print("Erro ao excluir, erro: " + e);
		}
		System.out.println("Setor excluido");
	}
}
