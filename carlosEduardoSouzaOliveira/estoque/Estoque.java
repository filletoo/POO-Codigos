package carlosEduardoSouzaOliveira.estoque;

public class Estoque {
	private Produto[] VetordeProdutos = new Produto[100];
	private int k = 0;
    
    public Produto procurar(int cod){
        for (int i = 0; i < k; i++){
            if (VetordeProdutos[i].getCod() == cod) return VetordeProdutos[i];
        }
        return null;
    }
    
    public void incluir(Produto p){
    	if (procurar(p.getCod()) != null) return;
        VetordeProdutos[k++] = p;
    }

    public void comprar(int cod, int quant, double preco){
    	if (quant <= 0 || preco <= 0) return;
        Produto p = procurar(cod);
        if (p != null) p.compra(quant, preco);
    }
    
    public double vender(int cod, int quant){
    	if (quant <= 0) return -1;
        Produto p = procurar(cod);
        if (p != null) return p.venda(quant);
        return -1;
    }

    public int quantidade(int cod){
        Produto p = procurar(cod);
        if (p != null) return p.getQuant();
        return -1;
    }
    
    public String movimentacao(int cod) {
    	Produto p = procurar(cod);
    	if (p != null) return p.getMovimentacao();
    	return null;
    }
    
    public Fornecedor[] fornecedores(int cod) {
    	Produto p = procurar(cod);
    	if (p != null) return p.getFornecedores();
    	return null;
    }
    
    public Produto[] estoqueAbaixoDoMinimo() {
    	Produto[] vetor1 = new Produto[100];
    	int x = 0;
    	for (int i = 0; i < k; i++) {
	    	if (VetordeProdutos[i].getQuant() < VetordeProdutos[i].getMin()) {
	    		vetor1[x++] = VetordeProdutos[i];
	    	}
    	}
    	Produto[] copia = new Produto[x];
    	for (int i = 0; i < x; i++) {
    		copia[i] = vetor1[i];
    	}
    	return copia;
    }
    
    public void adicionarFornecedor(int cod, Fornecedor f) {
    	Produto p = procurar(cod);
    	if (p != null) p.incluirfornecedor(f);
    }
    
    public double precoDeVenda(int cod) {
    	Produto p = procurar(cod);
    	if (p != null) return p.getPvenda();
    	return -1;
    	
    };
    public double precoDeCompra(int cod) {
    	Produto p = procurar(cod);
    	if (p != null) return p.getPcompra();
    	return -1;
    };
}
