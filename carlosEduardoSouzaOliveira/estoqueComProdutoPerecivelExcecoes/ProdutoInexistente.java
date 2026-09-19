package carlosEduardoSouzaOliveira.estoqueComProdutoPerecivelExcecoes;

public class ProdutoInexistente extends Exception{
	public ProdutoInexistente(int n) {
		super("Não há um produto de código " + n + " no estoque");
	}
}
