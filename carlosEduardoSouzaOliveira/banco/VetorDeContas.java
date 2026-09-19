package carlosEduardoSouzaOliveira.banco;

public class VetorDeContas implements RepositorioDeContas {
	private ContaAbstrata contas[] = new ContaAbstrata[10];
	private int posicao = 0;
	
	public boolean incluir(ContaAbstrata c) {
		ContaAbstrata outra = procurar(c.getNum());
		if (outra == null) {
		   contas[posicao++] = c;
		   return true;
		} return false;
	}
	
	public ContaAbstrata procurar(int n) {
		for (int i = 0; i < posicao; i++) {
			if (contas[i].getNum() == n) {
				return contas[i];
			}
		}
		return null;
	}
}
