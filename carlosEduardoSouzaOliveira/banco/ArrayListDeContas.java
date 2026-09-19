package carlosEduardoSouzaOliveira.banco;

import java.util.ArrayList;

public class ArrayListDeContas implements RepositorioDeContas {
	
	private ArrayList<ContaAbstrata> lista = new ArrayList<ContaAbstrata>();

	public boolean incluir(ContaAbstrata c) {
		ContaAbstrata outra = procurar(c.getNum());
		if (outra == null) {
		  lista.add(c);
		  return true;
		}
		return false;
	}
	
	public ContaAbstrata procurar(int n) {
		for (ContaAbstrata conta : lista) {
			if (conta.getNum() == n)
				return conta;
		}
		return null;
	}
}
