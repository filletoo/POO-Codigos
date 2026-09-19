package carlosEduardoSouzaOliveira.banco;

public class ExecutaConta {

	public static void main(String[] args) throws ExcecaoSaldoInsuficiente, ExcecaoContaJaCadastrada, ExcecaoContaInexistente {
		
		RepositorioDeContas rep = new VetorDeContas();
		Banco b = new Banco(rep);
		ContaAbstrata c = new ContaComum();
		c.setNum(1);
		ContaAbstrata c1 = new ContaComum();
		c1.setNum(11);
		b.adicionar(c);
		b.adicionar(c1);
		
		Pessoa p1 = new Pessoa();
		p1.setCpf(1);
		p1.setNome("Pedro");
		
		c.credito(100);
		c1.credito(11);
		b.deposito(1, 98);
		b.deposito(11, 8);
		c.debito(30);
		c1.debito(3);
		b.saque(1, 75);
		b.saque(11, 5);
		c.setDono(p1);
		
		System.out.println(c.getExtrato());
		System.out.println(c1.getExtrato());
	}
}
