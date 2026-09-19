package carlosEduardoSouzaOliveira.estoqueComProdutoPerecivelExcecoes;

public class ProdutoNaoPerecivel extends Exception{
	public ProdutoNaoPerecivel(int n) {
		super("O produto de código " + n + " já está cadastrado");
	}
}
