package carlosEduardoSouzaOliveira.banco;

public class ContaEspecial extends ContaAbstrata {
	
	private double limite;

	public ContaEspecial() {
		super();
	}
	
	public ContaEspecial(int n, double l) {
		super(n);
		limite = l;	
	}
	
	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	public void debito(double v) throws ExcecaoSaldoInsuficiente {
		if (v <= saldo + limite) {
			saldo = saldo - v;
			extrato = extrato + "Conta: " + num + ". Debito: " + v + ". Saldo: " + saldo + "\n";
		}else {
			throw new ExcecaoSaldoInsuficiente(num, saldo, v);
		}
	} 
}
