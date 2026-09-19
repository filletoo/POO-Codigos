package carlosEduardoSouzaOliveira.estoqueLista;

public class NoDaListaDeProdutos {
	private Produto produto;
	private NoDaListaDeProdutos prox;
	
	public void incluir(Produto c) {
		if (prox == null) {
			if (produto == null) {
				produto = c;
				return;
			}
			NoDaListaDeProdutos novo = new NoDaListaDeProdutos();
			novo.produto = c;
			prox = novo;
			novo.prox = null;
		}else {
			prox.incluir(c);
		}
	}
	
	public Produto procurar(int cod) {
		if (produto != null) {
			if (produto.getCod() == cod) {
				return produto;
			}
			if (prox != null) {
				return prox.procurar(cod);
			}else {
				return null;
			}
		}
		return null;
	}
	
	public NoDaListaDeProdutos getProx() {
		return prox;
	}
	
	public Produto getProduto() {
		return produto;
	}
}
