package com.hepta.persistence.test;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.hepta.entity.*;
import com.hepta.persistence.FuncionarioDAO;

public class FuncionarioDAOTest {
	private static Funcionario funcionario;
	private static Setor setor;
	private static FuncionarioDAO dao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		funcionario = new Funcionario();
		setor = new Setor();
		dao = new FuncionarioDAO();
	}

	@Test
	void createFuncionario() {
		setor.setId(4);
		funcionario.setNome("TesteNome3");
		funcionario.setSalario(1500.00);
		funcionario.setIdade(21);
		funcionario.setSetor(setor);
		try {
			dao.create(funcionario);
		} catch (Exception e) {
			System.out.println("Erro ao criar funcionario, erro: " + e);
		}
		System.out.println("Funcionario criado");
	}

	@Test
	void getAllTest() {
		List<Funcionario> funcionarios = null;
		try {
			funcionarios = dao.getAll();
			for (Funcionario f : funcionarios) {
				System.out.println("ID:" + f.getId());
				System.out.println("Nome:" + f.getNome());
				System.out.println("Salario:" + f.getSalario());
				System.out.println("Idade:" + f.getIdade());
				System.out.println("Setor:" + f.getSetor().getNome());
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Erro ao ler funcionarios, erro: " + e);
		}
	}

	@Test
	void updateTest() {
		funcionario.setId(4);
		setor.setId(2);
		funcionario.setNome("TesteNome4");
		funcionario.setSalario(2000.00);
		funcionario.setIdade(22);
		funcionario.setSetor(setor);
		try {
			dao.update(funcionario);
		} catch (Exception e) {
			System.out.println("Erro ao atualizar  funcionarios, erro: " + e);
		}

		System.out.println("Funcionario atualizado");
	}

	@Test
	void findTest() {
		try {
			Funcionario f = dao.find(3);
			System.out.println("ID:" + f.getId());
			System.out.println("Nome:" + f.getNome());
			System.out.println("Salario:" + f.getSalario());
			System.out.println("Idade:" + f.getIdade());
			System.out.println("Setor:" + f.getSetor().getNome());
			System.out.println();

		} catch (Exception e) {
			System.out.println("Erro ao buscar funcionario, erro: " + e);
		}

	}

	@Test
	void deleteTest() {
		try {
			dao.delete(4);
		} catch (Exception e) {
			System.out.println("Erro ao excluir funcionario, erro: " + e);
		}
		System.out.print("Funcionario excluido");
	}
}
