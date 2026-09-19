package carlosEduardoSouzaOliveira.banco;

public class ContaImposto extends ContaAbstrata {
	
	private double imposto;
	
	public ContaImposto() {
		super();
	}
	
	public ContaImposto(int n, double i) {
		super(n);
		imposto = i;	
	}
	
	public double getImposto() {
		return imposto;
	}
	
	public void debito(double v) throws ExcecaoSaldoInsuficiente {
		double imp = v * imposto;
		if (v + imp <= saldo) {
			saldo = saldo - v - imp;
			extrato = extrato + "Conta: " + num + ". Debito: " + v + ". Saldo: " + saldo + "\n";
		}else{
			throw new ExcecaoSaldoInsuficiente(num, saldo, v);
		}
	} 
}
