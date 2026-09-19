package carlosEduardoSouzaOliveira.banco;

public class ExcecaoSaldoInsuficiente extends Exception {
	public ExcecaoSaldoInsuficiente(int n, double s, double v) {
		super("Saldo insuficiente para a conta " + n + 
				". Saldo atual: "+ s + ". Valor a ser debitado: " + 
				v + ".");
	}
}
