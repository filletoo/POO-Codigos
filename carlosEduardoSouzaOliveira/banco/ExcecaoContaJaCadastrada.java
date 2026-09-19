package carlosEduardoSouzaOliveira.banco;

public class ExcecaoContaJaCadastrada extends Exception {
	public ExcecaoContaJaCadastrada(int n) {
		super("A conta de número " + n + " já está cadastrada");
	}
}
