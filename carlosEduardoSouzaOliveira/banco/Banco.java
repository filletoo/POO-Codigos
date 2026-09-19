package carlosEduardoSouzaOliveira.banco;


public class Banco {

	private RepositorioDeContas contas;
	
	public Banco (RepositorioDeContas rep) {
		this.contas = rep;
	}
	
	public boolean adicionar(ContaAbstrata c) throws ExcecaoContaJaCadastrada {
		if (!contas.incluir(c)) {
			throw new ExcecaoContaJaCadastrada(c.getNum());
		}return true;
	}
	
	public void juros(int n, double tx) throws ExcecaoContaInexistente, ExcecaoNaoEhPoupanca {
		ContaAbstrata c = contas.procurar(n);
		if (c != null && c instanceof Poupanca) {
		   ((Poupanca) c).rendeJuros(tx);
		}else if (c == null) {
			throw new ExcecaoContaInexistente(n);
		}else {
			throw new ExcecaoNaoEhPoupanca(n);
		}
	}
	
	public double saldo(int n) throws ExcecaoContaInexistente {
		ContaAbstrata c = contas.procurar(n);
		if (c != null) {
		   return c.getSaldo();
		}else {
			throw new ExcecaoContaInexistente(n);
		}
	}
	
	public String extrato(int n) throws ExcecaoContaInexistente {
		ContaAbstrata c = contas.procurar(n);
		if (c != null) {
			return c.getExtrato();
		}else {
			throw new ExcecaoContaInexistente(n);
		}
	}
	
	public void saque(int n, double v) throws ExcecaoSaldoInsuficiente, ExcecaoContaInexistente {
		ContaAbstrata c = contas.procurar(n);
		if (c != null) {
			c.debito(v);
		}else {
			throw new ExcecaoContaInexistente(n);
		}
	}
	
	public void deposito(int n, double v) throws ExcecaoContaInexistente {
		ContaAbstrata c = contas.procurar(n);
		if (c != null) {
			c.credito(v);
		}else {
			throw new ExcecaoContaInexistente(n);
		}
	}	
	
	public void transfere(int de, int para, double v) throws ExcecaoContaInexistente, ExcecaoSaldoInsuficiente{
		ContaAbstrata c_de = contas.procurar(de);
		ContaAbstrata c_para = contas.procurar(para);
		
		if (c_de == null) {
			throw new ExcecaoContaInexistente(de);
		}
		if (c_para == null) {
			throw new ExcecaoContaInexistente(para);
		}
		
		//debito vai jogar o erro se der
		c_de.debito(v);
		
		c_para.credito(v);
	}
	
	public Pessoa dono(int n) throws ExcecaoContaInexistente {
		ContaAbstrata c = contas.procurar(n);
		if (c != null) {
			return c.getDono();
		}else {
			throw new ExcecaoContaInexistente(n);
		}
	}	
}
