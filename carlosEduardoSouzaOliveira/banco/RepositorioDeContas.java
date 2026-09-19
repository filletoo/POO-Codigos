package carlosEduardoSouzaOliveira.banco;

public interface RepositorioDeContas {

	public boolean incluir(ContaAbstrata c);
	
	public ContaAbstrata procurar(int n);
}
