package carlosEduardoSouzaOliveira.banco;

public class NoDaListaDeContas implements RepositorioDeContas {
	
	private ContaAbstrata conta;
	private NoDaListaDeContas prox;
	
	public boolean incluir(ContaAbstrata c) {
		if (procurar(c.getNum()) != null) {
			return false;
		}
		if (prox == null) {
			conta = c;
			NoDaListaDeContas novo = new NoDaListaDeContas();
			prox = novo;
			return true;
		} else {
			return prox.incluir(c);
		}
	}
	
	public ContaAbstrata procurar(int n) {
		if (conta == null) {
			return null;
		}
		if (conta != null && conta.getNum() == n) {
			return conta;
		} else {
 		    return prox.procurar(n);
		}
	}
}
