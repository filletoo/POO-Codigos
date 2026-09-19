package carlosEduardoSouzaOliveira.banco;

public class ContaComum extends ContaAbstrata {

	public ContaComum() {
		super();
	}
	
	public ContaComum(int n) {
		super(n);
	}
	
	public void debito(double v) throws ExcecaoSaldoInsuficiente {
		if (v <= saldo) {
		  saldo = saldo - v;
		  extrato = extrato + "Conta: " + num + ". Debito: " + v + ". Saldo: " + saldo + "\n";
		} else {
			throw new ExcecaoSaldoInsuficiente(num, saldo, v);
		}
	}
}
