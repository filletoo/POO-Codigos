package carlosEduardoSouzaOliveira.banco;

public class ExcecaoContaInexistente extends Exception {
	public ExcecaoContaInexistente(int n) {
		super("Não há uma conta de número " + n + " registrada");
	}
}
