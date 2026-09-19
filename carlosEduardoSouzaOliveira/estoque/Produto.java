package carlosEduardoSouzaOliveira.estoque;

public class Produto {
	private int cod;
	private String desc;
    private int min;
    private double lucro;
    private double pcompra = 0; //preco de compra
    private double pvenda; //preco de venda
    private int quant = 0;
    private String movimentacao;
    private Fornecedor[] fornecedores = new Fornecedor[100];
    private int k = 0;
    
	public Produto(int cod, String desc, int min, double lucro){
        this.cod = cod;
        this.desc = desc;
        this.min = min;
        this.lucro = lucro;
        this.movimentacao = "Cod: " + cod + "\n" + "Sem Movimentação";
    }
    
    public void compra(int quant, double val){
    	if (quant <= 0 || val <= 0) return;
        pcompra = (this.quant*pcompra + quant*val)/(this.quant + quant);
        this.quant += quant;
        pvenda = pcompra*(1 + lucro);
        movimentacao = movimentacao.replace("Sem Movimentação", "");
        movimentacao += "Comprados: " + quant + " Estoque: " + this.quant + "\n";
    }

    public double venda(int quantv){
        if (quantv > quant || quantv <= 0) return -1;
        else quant -= quantv;
        movimentacao += "Vendidos: " + quantv + " Estoque: " + this.quant + "\n";
        return quantv*pvenda;
    }
    
    public void incluirfornecedor(Fornecedor f) {
    	for (int i = 0; i < k; i++) {
    		if (fornecedores[i] == f) return;
    	}
    	fornecedores[k++] = f;
    }
    
    //metodos de pegar e atribuir atributos
	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public double getPvenda() {
		return pvenda;
	}

	public void setPvenda(double pvenda) {
		this.pvenda = pvenda;
	}

	public double getPcompra() {
		return pcompra;
	}

	public void setPcompra(double pcompra) {
		this.pcompra = pcompra;
	}

	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public Fornecedor[] getFornecedores() {
		Fornecedor[] copia = new Fornecedor[k];
		for (int i = 0; i < k; i++) {
			copia[i] = fornecedores[i];
		}
		return copia;
	}

	public String getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(String movimentacao) {
		this.movimentacao = movimentacao;
	}
    
}
