package carlosEduardoSouzaOliveira.estoqueComProdutoPerecivelExcecoes;

public class ProdutoJaCadastrado extends Exception {
	public ProdutoJaCadastrado(int n) {
		super("O produto de código " + n + " já está cadastrado");
	}
}
