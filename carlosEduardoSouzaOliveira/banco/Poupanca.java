package carlosEduardoSouzaOliveira.banco;

public class Poupanca extends ContaComum {
	
	public Poupanca() {
		super();
	}
	
	public Poupanca(int n) {
		super(n);
	}
	
	public void rendeJuros(double taxa) {
		double valor = getSaldo() * taxa;
		credito(valor);
	}

}
