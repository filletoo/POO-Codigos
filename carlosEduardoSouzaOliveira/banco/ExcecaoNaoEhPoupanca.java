package carlosEduardoSouzaOliveira.banco;

public class ExcecaoNaoEhPoupanca extends Exception {
	public ExcecaoNaoEhPoupanca(int n) {
		super("A conta de número " + n + " não é uma poupança");
	}
}
