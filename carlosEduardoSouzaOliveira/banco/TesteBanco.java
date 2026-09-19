package carlosEduardoSouzaOliveira.banco;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class TesteBanco {

	@Test
	public void testarCreditoDebito() throws ExcecaoSaldoInsuficiente, ExcecaoContaJaCadastrada, ExcecaoContaInexistente {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		ContaAbstrata c2 = new ContaComum();
		c2.setNum(2);
		b.adicionar(c1);
		b.adicionar(c2);
		
		b.deposito(1, 111);
		b.deposito(2, 222);
		b.saque(1, 11);
		b.saque(2, 22);
		
		assertEquals(100, b.saldo(1), 0.001);
		assertEquals(200, b.saldo(2), 0.001);
	}
	
	@Test
	public void testarCreditoDebitoEmPoupanca() throws ExcecaoSaldoInsuficiente, ExcecaoContaJaCadastrada, ExcecaoContaInexistente {
		RepositorioDeContas rep = new ArrayListDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new Poupanca();
		c1.setNum(1);
		ContaAbstrata c2 = new ContaComum();
		c2.setNum(2);
		b.adicionar(c1);
		b.adicionar(c2);
		
		b.deposito(1, 111);
		b.deposito(2, 222);
		b.saque(1, 11);
		b.saque(2, 22);
		
		assertEquals(100, b.saldo(1), 0.001);
		assertEquals(200, b.saldo(2), 0.001);
	}
	
	@Test
	public void testarJurosEmPoupanca() throws ExcecaoContaJaCadastrada, ExcecaoContaInexistente, ExcecaoNaoEhPoupanca {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new Poupanca();
		c1.setNum(1);
		b.adicionar(c1);
		
		b.deposito(1, 300);
		b.juros(1, 0.01);
		
		assertEquals(303, b.saldo(1), 0.001);
	}
	
	@Test
	public void testarJurosEmConta() throws ExcecaoContaJaCadastrada, ExcecaoContaInexistente, ExcecaoNaoEhPoupanca {
		RepositorioDeContas rep = new NoDaListaDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		b.adicionar(c1);
		
		b.deposito(1, 300);
		try {
			b.juros(1, 0.01);
			fail("Não era pra ter dado certo o juros!");
		}catch (ExcecaoNaoEhPoupanca e){
			//comportamento esperado
		}
		
		assertEquals(300, b.saldo(1), 0.001);
	}
	
	@Test
	public void testarCadastrarContaJaCadastrada() throws ExcecaoContaJaCadastrada {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		ContaAbstrata c2 = new ContaComum();
		c2.setNum(2);
		assertTrue(b.adicionar(c1));
		assertTrue(b.adicionar(c2));
		try {
			b.adicionar(c1);
			fail("Não era pra ter dado certo a inclusao!");
		}catch (ExcecaoContaJaCadastrada e) {
			//esperado
		}
	}
	
	@Test
	public void testarSaldoDeContaInexistente() throws ExcecaoContaInexistente, ExcecaoContaJaCadastrada {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		assertTrue(b.adicionar(c1));
		b.deposito(1, 100);
		assertEquals(100, b.saldo(1), 0.0001);
		try {
			b.saldo(2);
			fail("Não deveria ter dado certo, pois a conta não existe");
		}catch (ExcecaoContaInexistente e){
			//esperado
		}
	}
	
	@Test
	public void testarExtrato() throws ExcecaoSaldoInsuficiente, ExcecaoContaInexistente, ExcecaoContaJaCadastrada {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		assertTrue(b.adicionar(c1));
		b.deposito(1, 100);
		b.saque(1, 19);
		b.saque(1, 11);
		b.saque(1, 65);
		b.deposito(1, 5);
		System.out.println(b.extrato(1));
		assertEquals("Conta: 1. Credito: 100.0. Saldo: 100.0\n"
				+ "Conta: 1. Debito: 19.0. Saldo: 81.0\n"
				+ "Conta: 1. Debito: 11.0. Saldo: 70.0\n"
				+ "Conta: 1. Debito: 65.0. Saldo: 5.0\n"
				+ "Conta: 1. Credito: 5.0. Saldo: 10.0\n", b.extrato(1));
		try{
			b.extrato(5);
			fail("Não deveria ter dado certo, pois a conta não existe");
		}catch (ExcecaoContaInexistente e) {
			//comportamento esperado
		}
	}
	
	@Test
	public void testarSaqueContaInexistente() throws ExcecaoSaldoInsuficiente, ExcecaoContaJaCadastrada, ExcecaoContaInexistente {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		assertTrue(b.adicionar(c1));
		b.deposito(1, 100);
		b.saque(1, 19);
		assertEquals(81, b.saldo(1), 0.0001);
		try{
			b.saldo(5);
			fail("Não deveria ter dado certo, pois a conta não existe");
		}catch (ExcecaoContaInexistente e) {
			//esperado
		}
		
		try{
			b.saque(5, 100);
			fail("Não deveria ter dado certo, pois a conta não existe");
		}catch (ExcecaoContaInexistente e) {
			//esperado
		}
		
		try{
			b.saldo(5);
			fail("Não deveria ter dado certo, pois a conta não existe");
		}catch (ExcecaoContaInexistente e) {
			//esperado
		}
	}
	
	@Test
	public void testarDepositoContaInexistente() throws ExcecaoSaldoInsuficiente, ExcecaoContaJaCadastrada, ExcecaoContaInexistente {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		assertTrue(b.adicionar(c1));
		b.deposito(1, 100);
		b.saque(1, 19);
		assertEquals(81, b.saldo(1), 0.0001);
		try{
			b.saldo(5);
			fail("Não deveria ter dado certo, pois a conta não existe");
		}catch (ExcecaoContaInexistente e){
			//esperado
		}
		
		try{
			b.deposito(5, 100);
			fail("Não deveria ter dado certo, pois a conta não existe");
		}catch (ExcecaoContaInexistente e){
			//esperado
		}
		
		try{
			b.saldo(5);
			fail("Não deveria ter dado certo, pois a conta não existe");
		}catch (ExcecaoContaInexistente e){
			//esperado
		}
	}
	
	@Test
	public void testarSaqueAcimaSaldoEmConta() throws ExcecaoContaInexistente, ExcecaoContaJaCadastrada {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		assertTrue(b.adicionar(c1));
		b.deposito(1, 100);
		try {
			b.saque(1, 101);
			fail("Não era pra ter dado certo o saque!");
		} catch (ExcecaoSaldoInsuficiente e) {
			// Comportamento esperado! Deu excecao de saldo insuficiente!
		}
		assertEquals(100, b.saldo(1), 0.0001);
	}
	
	@Test
	public void testarSaqueAcimaSaldoEmContaEspecial() throws ExcecaoSaldoInsuficiente, ExcecaoContaInexistente, ExcecaoContaJaCadastrada {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaEspecial(1, 100);
		c1.setNum(1);
		assertTrue(b.adicionar(c1));
		b.deposito(1, 100);
		b.saque(1, 101);
		assertEquals(-1, b.saldo(1), 0.0001);
		b.saque(1, 99);
		assertEquals(-100, b.saldo(1), 0.0001);
		try {
			b.saque(1, 1);
			fail("Não era pra ter dado certo o saque!");
		} catch (ExcecaoSaldoInsuficiente e) {
			// Comportamento esperado. Erro no saque por falta de saldo;
		}
		assertEquals(-100, b.saldo(1), 0.0001);
	}
	
	@Test
	public void testarSaqueEmContaImposto() throws ExcecaoSaldoInsuficiente, ExcecaoContaInexistente, ExcecaoContaJaCadastrada {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaImposto(1, 0.01);
		c1.setNum(1);
		assertTrue(b.adicionar(c1));
		b.deposito(1, 100);
		try {
			b.saque(1, 100);
			fail("Não era pra ter dado certo o saque!");
		} catch (ExcecaoSaldoInsuficiente e) {
			// Comportamento correto. Nao pode sacar o saldo pois tem imposto adicional!
		}
		assertEquals(100, b.saldo(1), 0.0001);
		b.saque(1, 99);
		assertEquals(0.01, b.saldo(1), 0.0001);
	}
	
	@Test
	public void testarAdicionarContaComDono() throws ExcecaoContaJaCadastrada, ExcecaoContaInexistente {
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(1);
		Pessoa p = new Pessoa();
		p.setCpf(1);
		p.setNome("Pedro");
		c1.setDono(p);
		assertTrue(b.adicionar(c1));
		assertEquals("Pedro", b.dono(1).getNome());
		assertEquals(1, b.dono(1).getCpf());
	}
	
}
