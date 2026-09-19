package carlosEduardoSouzaOliveira.banco;

public abstract class ContaAbstrata {
	protected int num;
	protected double saldo;
	protected String extrato = "";
	protected Pessoa dono;
	
	public ContaAbstrata() {
	}
	
	public ContaAbstrata(int n) {
		num = n;
	}
	
	public ContaAbstrata(int n, Pessoa p) {
		num = n;
		dono = p;
	}
	
	public Pessoa getDono() {
		return dono;
	}

	public void setDono(Pessoa dono) {
		this.dono = dono;
	}

	public void setNum(int n) {	
	   num = n;	
	}
	
	public void credito(double v) {
		saldo = saldo + v;
		extrato = extrato + "Conta: " + num + ". Credito: " + v + ". Saldo: " + saldo + "\n";
	}
	
	public abstract void debito(double v) throws ExcecaoSaldoInsuficiente;
	
	public String getExtrato() {
		return extrato;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public int getNum() {
		return num;
	}
}
