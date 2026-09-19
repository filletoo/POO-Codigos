package carlosEduardoSouzaOliveira.estoqueComProdutoPerecivelExcecoes;

public class ProdutoVencido extends Exception{
	public ProdutoVencido(int n) {
		super("O produto de código " + n + " já está cadastrado");
	}
}
